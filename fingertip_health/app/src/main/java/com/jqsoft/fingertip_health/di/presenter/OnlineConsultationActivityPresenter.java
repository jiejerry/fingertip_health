package com.jqsoft.fingertip_health.di.presenter;

import android.content.Context;
import android.content.SharedPreferences;

import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.bean.base.HttpResultBaseBean;
import com.jqsoft.fingertip_health.bean.response_new.OnlineConsultationResultBean;
import com.jqsoft.fingertip_health.di.contract.OnlineConsultationActivityContract;
import com.jqsoft.fingertip_health.di.ui.activity.base.AbstractActivity;
import com.jqsoft.fingertip_health.di_http.http.grassroots_civil_administration_platform.GCAService;
import com.jqsoft.fingertip_health.rx.BaseSubscriber;
import com.jqsoft.fingertip_health.util.Util;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import okhttp3.RequestBody;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/5/21.
 */

public class OnlineConsultationActivityPresenter implements OnlineConsultationActivityContract.presenter {

    private final OnlineConsultationActivityContract.View view;
    private final SharedPreferences sharedPreferences;
    private final GCAService GCAService;


    @Inject
    public OnlineConsultationActivityPresenter(OnlineConsultationActivityContract.View view,
                                               @Named("default") SharedPreferences sharedPreferences,
                                               GCAService GCAService) {
        this.view = view;
        this.sharedPreferences = sharedPreferences;
        this.GCAService = GCAService;
    }

    public void main(Map<String, String> map, final boolean isLoadMore) {
        final Context context = (Context)view;
        AbstractActivity abstractActivity = (AbstractActivity) context;
        if (!Util.isNetworkConnected(context)){
            view.onLoadListFailure(Constants.HINT_LOADING_DATA_FAILURE, isLoadMore);
            return;
        }
        RequestBody body = Util.getBodyFromMap(map);

        GCAService.getOnlineConsultationData(body)
                .compose(abstractActivity.<HttpResultBaseBean<List<OnlineConsultationResultBean>>>bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<HttpResultBaseBean<TreatmentListResultWrapperBean>>() {
                 .subscribe(new BaseSubscriber<HttpResultBaseBean<List<OnlineConsultationResultBean>>>(context) {
                        @Override
                    public void onStart() {
                        super.onStart();
//                        Util.showGifProgressDialog(((Context)view));
                    }

                    @Override
                    public void onCompleted() {
                        super.onCompleted();
//                        boolean isMainThread = Util.isMainThread();
//                        LogUtil.i("onCompleted runs in main thread:"+isMainThread);
//                        final Activity activity = (Activity) view;
//                        activity.runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                Util.hideGifProgressDialog(activity);
//
//                            }
//                        });

                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        view.onLoadListFailure(e.getMessage(), isLoadMore);
//                        Activity activity = (Activity) view;
//                        Util.hideGifProgressDialog(activity);

//                        if (isLoadMore){
//                            view.onLoadMoreListSuccess(null);
//                        } else {
//                            view.onLoadListSuccess(null);
//                        }

                    }

                    @Override
                    public void onNext(HttpResultBaseBean<List<OnlineConsultationResultBean>> bean) {
                        Util.decodeBase64Bean(bean);
                        boolean isSuccessful = Util.isResponseSuccessful(bean);
                        boolean isResponseTokenTimeout = Util.isResponseTokenTimeout(bean);
                        if (isSuccessful){
                            if (isLoadMore){
                                view.onLoadMoreListSuccess(bean);
                            } else {
                                view.onLoadListSuccess(bean);
                            }
                        } else if (isResponseTokenTimeout) {
                            Util.gotoLoginActivity(context, true, true);
                        } else{
                            String msg = Util.getMessageFromHttpResponse(bean);
                            view.onLoadListFailure(msg, isLoadMore);
                        }

                    }


                });

    }



}