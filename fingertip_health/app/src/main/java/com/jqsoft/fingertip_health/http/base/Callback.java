package com.jqsoft.fingertip_health.http.base;

import com.blankj.utilcode.utils.NetworkUtils;
import com.blankj.utilcode.utils.ToastUtils;
import com.jqsoft.fingertip_health.base.AppConstants;
import com.jqsoft.fingertip_health.presenter.base.BaseView;
import com.jqsoft.fingertip_health.utils.LogUtil;

import rx.Subscriber;

/**
 * Created by quantan.liu on 2017/3/21.
 */

public class Callback<T> extends Subscriber<T> {
    private Stateful target;

    public void setTarget(Stateful target) {
        this.target = target;
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        onfail();
    }

    @Override
    public void onNext(T data) {
        //// 2017/3/22 这边网络请求成功返回都不一样所以不能在这里统一写了（如果是自己公司需要规定一套返回方案）
        /// 2017/3/22 这里先统一处理为成功   我们要是想检查返回结果的集合是否是空，只能去子类回掉中完成了。
        LogUtil.i("Callback onNext");
        target.setState(AppConstants.STATE_SUCCESS);
        onResponse();
        onResponse(data);
    }

    public void onResponse(T data) {
        /**
         * 如果喜欢统一处理成功回掉也是可以的。
         * 不过获取到的数据都是不规则的，理论上来说需要判断该数据是否为null或者list.size()是否为0
         * 只有不成立的情况下，才能调用成功方法refreshView/()。如果统一处理就放在每个refreshView中处理。
         */
        LogUtil.i("Callback onResponse(T data):will call refreshView");
        ((BaseView) target).refreshView(data);
    }

    public void onResponse() {
    }

    public void onfail() {
        if (!NetworkUtils.isAvailableByPing()) {
            ToastUtils.showShortToast("你连接的网络有问题，请检查路由器");
            if (target != null) {
                target.setState(AppConstants.STATE_ERROR);
//                target.setState(AppConstants.STATE_SUCCESS);
            }
            return;
        }
//        ToastUtils.showShortToast("程序员哥哥偷懒去了，快去举报他");
        if (target != null) {
            target.setState(AppConstants.STATE_EMPTY);
//            target.setState(AppConstants.STATE_SUCCESS);
        }
    }
}
