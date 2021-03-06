package com.jqsoft.fingertip_health.di.presenter;

import android.content.Context;
import android.content.SharedPreferences;

import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.bean.fingertip.HttpResultBaseBeanForFingertip;
import com.jqsoft.fingertip_health.di.contract.LoginContractForFingertip;
import com.jqsoft.fingertip_health.di.ui.activity.LoginActivityNew;
import com.jqsoft.fingertip_health.di.ui.activity.base.AbstractActivity;
import com.jqsoft.fingertip_health.di_http.http.grassroots_civil_administration_platform.GCAService;
import com.jqsoft.fingertip_health.rx.RxBus;
import com.jqsoft.fingertip_health.util.Util;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import okhttp3.RequestBody;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/5/21.
 */

public class LoginPresenterForFingertip implements LoginContractForFingertip.presenter {

    private final LoginContractForFingertip.View view;
    private final SharedPreferences sharedPreferences;
    private final GCAService GCAService;


    @Inject
    public LoginPresenterForFingertip(LoginContractForFingertip.View view,
                                      @Named("default") SharedPreferences sharedPreferences,
                                      GCAService GCAService) {
        this.view = view;
        this.sharedPreferences = sharedPreferences;
        this.GCAService = GCAService;
    }

    public void login(Map<String, String> map){
        final Context context = (Context)view;
        Util.showGifProgressDialog(context);
        AbstractActivity abstractActivity = (AbstractActivity) context;
        if (!Util.isNetworkConnected(context)){
            view.onLoginFailure(Constants.HINT_LOADING_DATA_FAILURE);
            return;
        }
        RequestBody body = Util.getBodyFromMap(map);
        GCAService.loginForFingertip(body)
                .compose(abstractActivity.<HttpResultBaseBeanForFingertip<String>>bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<HttpResultBaseBeanForFingertip<String>>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        Util.showGifProgressDialog(context);

                    }

                    @Override
                    public void onCompleted() {
                        Util.hideGifProgressDialog(context);

                    }

                    @Override
                    public void onError(Throwable e) {
                        Util.hideGifProgressDialog(context);
                        view.onLoginFailure(e.getMessage());
                    }

                    @Override
//                    public void onNext(HttpResultBaseBeanForFingertip<LoginResultForFingertip> bean) {
                    public void onNext(HttpResultBaseBeanForFingertip<String> bean) {
                        Util.decodeBase64Bean(bean);
                        boolean isSuccessful = Util.isResponseSuccessfulForFingertip(bean);
                        boolean isTimeout = Util.isResponseTokenTimeoutForFingertip(bean);
                        if (isSuccessful){
//                            Util.hideGifProgressDialog(context);
                            view.onLoginSuccess(bean);
                        } else if (isTimeout){
                            RxBus.getDefault().post(Constants.EVENT_TYPE_FINISH_ACTIVITY);
                            Util.gotoActivity(context, LoginActivityNew.class);
                        }
                        else {
                            String msg = Util.getMessageFromHttpResponseForFingertip(bean);
                            view.onLoginFailure(msg);
                        }
                    }

                });

    }


//    public void mainArea(Map<String, String> map){
//        final Context context = (Context)view;
//        AbstractActivity abstractActivity = (AbstractActivity) context;
//        if (!Util.isNetworkConnected(context)){
//            view.onLoginAreaFailure(Constants.HINT_LOADING_DATA_FAILURE);
//            return;
//        }
//        GCAService.loginAreaSRC(map)
//                .compose(abstractActivity.<HttpResultBaseBean<List<gdws_sys_area>>>bindToLifecycle())
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<HttpResultBaseBean<List<gdws_sys_area>>>() {
//                    @Override
//                    public void onStart() {
//                        super.onStart();
//                        Util.showGifProgressDialog(context);
//
//                    }
//
//                    @Override
//                    public void onCompleted() {
//                      //  Util.hideGifProgressDialog(context);
//
//                    }
//
//                    @Override
//                        public void onError(Throwable e) {
//                        Util.hideGifProgressDialog(context);
//                        view.onLoginAreaFailure(e.getMessage());
//                    }
//
//                    @Override
//                    public void onNext(HttpResultBaseBean<List<gdws_sys_area>> bean) {
////                        Util.decodeBase64Bean(bean);
//                        boolean isSuccessful = Util.isResponseSuccessful(bean);
//                        if (isSuccessful){
//                            view.onLoginAreaSuccess(bean);
//                        } else {
//                            String msg = Util.getMessageFromHttpResponse(bean);
//                            view.onLoginAreaFailure(msg);
//                        }
//                    }
//
//                });
//
//    }
//
//
//    public void mainDictionary(Map<String, String> map){
//        final Context context = (Context)view;
//        AbstractActivity abstractActivity = (AbstractActivity) context;
//        if (!Util.isNetworkConnected(context)){
//            view.onLoginDataDictionatyFailure(Constants.HINT_LOADING_DATA_FAILURE);
//            return;
//        }
//        GCAService.loginDictionarySRC(map)
//                .compose(abstractActivity.<HttpResultBaseBean<List<SRCLoginDataDictionaryBean>>>bindToLifecycle())
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<HttpResultBaseBean<List<SRCLoginDataDictionaryBean>>>() {
//                    @Override
//                    public void onStart() {
//                        super.onStart();
//                    //    Util.showGifProgressDialog(context);
//
//                    }
//
//                    @Override
//                    public void onCompleted() {
//                     //   Util.hideGifProgressDialog(context);
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Util.hideGifProgressDialog(context);
//                        view.onLoginDataDictionatyFailure(e.getMessage());
//                    }
//
//                    @Override
//                    public void onNext(HttpResultBaseBean<List<SRCLoginDataDictionaryBean>> bean) {
////                        Util.decodeBase64Bean(bean);
//                        boolean isSuccessful = Util.isResponseSuccessful(bean);
//                        if (isSuccessful){
//                            view.onLoginDataDictionatySuccess(bean);
//                        } else {
//                            String msg = Util.getMessageFromHttpResponse(bean);
//                            view.onLoginDataDictionatyFailure(msg);
//                        }
//                    }
//
//                });
//
//    }
//
//
//    public void mainSalvation(Map<String, String> map){
//        final Context context = (Context)view;
//        Util.showGifProgressDialog(context);
//
//        AbstractActivity abstractActivity = (AbstractActivity) context;
//        if (!Util.isNetworkConnected(context)){
//            view.onLoginSalvationFailure(Constants.HINT_LOADING_DATA_FAILURE);
//            return;
//        }
//        GCAService.loginSalvationSRC(map)
//                .compose(abstractActivity.<HttpResultBaseBean<List<SRCLoginSalvationBean>>>bindToLifecycle())
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<HttpResultBaseBean<List<SRCLoginSalvationBean>>>() {
//                    @Override
//                    public void onStart() {
//                        super.onStart();
//
//
//                    }
//
//                    @Override
//                    public void onCompleted() {
//                    //    Util.hideGifProgressDialog(context);
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Util.hideGifProgressDialog(context);
//                        view.onLoginSalvationFailure(e.getMessage());
//                    }
//
//                    @Override
//                    public void onNext(HttpResultBaseBean<List<SRCLoginSalvationBean>> bean) {
////                        Util.decodeBase64Bean(bean);
//                        boolean isSuccessful = Util.isResponseSuccessful(bean);
//                        if (isSuccessful){
//                            view.onLoginSalvationSuccess(bean);
//                        } else {
//                            String msg = Util.getMessageFromHttpResponse(bean);
//                            view.onLoginSalvationFailure(msg);
//                        }
//                    }
//
//                });
//
//    }
//    public void getOrganizationData(Map<String, String> map){
//        final Context context = (Context)view;
//        AbstractActivity abstractActivity = (AbstractActivity) context;
//        if (!Util.isNetworkConnected(context)){
//            view.onLoginSalvationFailure(Constants.HINT_LOADING_DATA_FAILURE);
//            return;
//        }
//        GCAService.longinOrganization(map)
//                .compose(abstractActivity.<HttpResultBaseBean<List<OrganizationBean>>>bindToLifecycle())
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<HttpResultBaseBean<List<OrganizationBean>>>() {
//                    @Override
//                    public void onStart() {
//                        super.onStart();
//                       // Util.showGifProgressDialog(context);
//
//                    }
//
//                    @Override
//                    public void onCompleted() {
//                        //    Util.hideGifProgressDialog(context);
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Util.hideGifProgressDialog(context);
//                        view.onLoginSalvationFailure(e.getMessage());
//                    }
//
//                    @Override
//                    public void onNext(HttpResultBaseBean<List<OrganizationBean>> bean) {
////                        Util.decodeBase64Bean(bean);
//                        boolean isSuccessful = Util.isResponseSuccessful(bean);
//                        if (isSuccessful){
//                            view.onLoginOrganizationSuccess(bean);
//                        } else {
//                            String msg = Util.getMessageFromHttpResponse(bean);
//                            view.onLoginOrganizationBeanFailure(msg);
//                        }
//                    }
//
//                });
//
//    }
//
//    public void getpCodeData(Map<String, String> map){
//        final Context context = (Context)view;
//        AbstractActivity abstractActivity = (AbstractActivity) context;
//        if (!Util.isNetworkConnected(context)){
//            view.onLoginPcodeBeanFailure(Constants.HINT_LOADING_DATA_FAILURE);
//            return;
//        }
//        GCAService.longinpCodeData(map)
//                .compose(abstractActivity.<HttpResultBaseBean<List<PcodeDataBean>>>bindToLifecycle())
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<HttpResultBaseBean<List<PcodeDataBean>>>() {
//                    @Override
//                    public void onStart() {
//                        super.onStart();
//                        // Util.showGifProgressDialog(context);
//
//                    }
//
//                    @Override
//                    public void onCompleted() {
//                        //    Util.hideGifProgressDialog(context);
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Util.hideGifProgressDialog(context);
//                        view.onLoginPcodeBeanFailure(e.getMessage());
//                    }
//
//                    @Override
//                    public void onNext(HttpResultBaseBean<List<PcodeDataBean>> bean) {
////                        Util.decodeBase64Bean(bean);
//                        boolean isSuccessful = Util.isResponseSuccessful(bean);
//                        if (isSuccessful){
//                            view.onLoginPcodeSuccess(bean);
//                        } else {
//                            String msg = Util.getMessageFromHttpResponse(bean);
//                            view.onLoginPcodeBeanFailure(msg);
//                        }
//                    }
//
//                });
//
//    }
}
