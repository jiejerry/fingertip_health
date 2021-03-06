package com.jqsoft.fingertip_health.di_http.http;

import android.text.TextUtils;

import com.jqsoft.fingertip_health.base.Constants;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.jessyan.retrofiturlmanager.RetrofitUrlManager;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * <b>类名称：</b> OkhttpModule <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> Lincoln <br/>
 * <b>修改人：</b> Lincoln <br/>
 * <b>修改时间：</b> 2016年08月12日 下午3:48<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */
@Module
public class OkhttpModule {

    @Singleton
    @Provides
    @Named("cache")
    public OkHttpClient providerAutoCacheOkHttpClient(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        Interceptor cacheInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Response response = chain.proceed(request);

                String cacheControl = request.cacheControl().toString();
                if (TextUtils.isEmpty(cacheControl)) {
                    cacheControl = "public, max-age=" + 3600 * 6 + " ,max-stale=2419200";
                }
                return response.newBuilder()
                        .header("Cache-Control", cacheControl)
                        .removeHeader("Pragma")
                        .build();
            }
        };
        return new OkHttpClient.Builder()
                .addNetworkInterceptor(interceptor)
                .addNetworkInterceptor(cacheInterceptor)
                .retryOnConnectionFailure(true)
                .connectTimeout(10, TimeUnit.SECONDS)
                .build();
    }

    @Singleton
    @Provides
    @Named("fingertip_health")
    public OkHttpClient providerSignedDoctorClientOkHttpClient(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        Interceptor cacheInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Response response = chain.proceed(request);

                String cacheControl = request.cacheControl().toString();
                if (TextUtils.isEmpty(cacheControl)) {
                    cacheControl = "public, max-age=" + 3600 * 6 + " ,max-stale=2419200";
                }
                return response.newBuilder()
                        .header("Cache-Control", cacheControl)
                        .removeHeader("Pragma")
//                        .addHeader("Content-Type", "application/json")
                        .build();
            }
        };
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .addNetworkInterceptor(interceptor)
                .addNetworkInterceptor(cacheInterceptor)
                .retryOnConnectionFailure(true)
                .connectTimeout(Constants.OKHTTP_CLIENT_CONNECT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .readTimeout(Constants.OKHTTP_CLIENT_READ_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .writeTimeout(Constants.OKHTTP_CLIENT_WRITE_TIMEOUT_SECONDS, TimeUnit.SECONDS);
        return RetrofitUrlManager.getInstance().with(builder)
                .build();
//        return new OkHttpClient.Builder()
//                .addNetworkInterceptor(interceptor)
//                .addNetworkInterceptor(cacheInterceptor)
//                .retryOnConnectionFailure(true)
//                .connectTimeout(Constants.OKHTTP_CLIENT_CONNECT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
//                .readTimeout(Constants.OKHTTP_CLIENT_READ_TIMEOUT_SECONDS, TimeUnit.SECONDS)
//                .writeTimeout(Constants.OKHTTP_CLIENT_WRITE_TIMEOUT_SECONDS, TimeUnit.SECONDS)
//                .build();
    }

    @Singleton
    @Provides
    @Named("default")
    public OkHttpClient providerOkHttpClient(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder()
                .addNetworkInterceptor(interceptor)
                .retryOnConnectionFailure(true)
                .connectTimeout(10, TimeUnit.SECONDS)
                .build();
    }
}
