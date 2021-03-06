package com.jqsoft.fingertip_health.di.youtuIdentify;

import android.util.Log;


import com.jqsoft.fingertip_health.base.Constants;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by qwy on 2016/10/19.
 * 网络请求工具类
 */
public class TecentHttpUtil {

    public static void uploadIdCard(String bitmap, String card_type, final SimpleCallBack callback) {
        StringBuffer mySign = new StringBuffer("");
        YoutuSign.appSign(Constants.AppID, Constants.SecretID, Constants.SecretKey,
                System.currentTimeMillis() / 1000 + Constants.EXPIRED_SECONDS,
                Constants.QQNumber, mySign);
        RequestParams params = new RequestParams("http://api.youtu.qq.com/youtu/ocrapi/idcardocr");
        params.setAsJsonContent(true);
        params.addHeader("accept", "*/*");
        params.addHeader("Host", "api.youtu.qq.com");
        params.addHeader("user-agent", "youtu-java-sdk");
        params.addHeader("Authorization", mySign.toString());
        params.addHeader("Content-Type", "text/json");
        params.addParameter("card_type", Integer.valueOf(card_type));
        params.addBodyParameter("image", bitmap);
        params.addBodyParameter("app_id", Constants.AppID);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("onSuccess",result);
                callback.Succ(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.d("onError",ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.d("onCancelled", cex.getMessage());
            }

            @Override
            public void onFinished() {

            }
        });

    }

    public interface SimpleCallBack {
        void Succ(String result);

        void error();
    }


}
