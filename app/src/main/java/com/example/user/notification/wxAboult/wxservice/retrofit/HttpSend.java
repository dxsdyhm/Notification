package com.example.user.notification.wxAboult.wxservice.retrofit;

import android.util.Log;

import com.example.user.notification.gloable.MyApp;
import com.example.user.notification.wxAboult.wxservice.entry.WXToken;
import com.example.user.notification.wxAboult.wxservice.wxService;
import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;

import java.io.IOException;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by dxs on 2017/11/17.
 * web 请求集合
 * 暂时未作封装，直接使用Retrofit的API，很多重复代码需要封装配置
 */

public class HttpSend {
    //    private final static String BASEURL="http://39.108.193.125:81/psp/ja/v1/";
//    private final static String BASEURL="http://vasapi.cloudlinks.cn:8085/psp/ja/v1/";
    private final static String BASEURL="http://45.77.198.212/";
    private static class HtpSendHolder{
        public static final HttpSend INSTENCE=new HttpSend();
    }
    private HttpSend (){

    }

    public static HttpSend getInstence(){
        return HtpSendHolder.INSTENCE;
    }
    ClearableCookieJar cookieJar;
    public void getWXToken(final String openid,final String templiId){
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        // Log信息拦截器
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);//这里可以选择拦截级别
        //设置 Debug Log 模式
        // init cookie manager
        if(cookieJar==null){
            cookieJar =
                    new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(MyApp.context));
        }
        builder.addInterceptor(loggingInterceptor)
                .cookieJar(cookieJar);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(builder.build())
                .build();

        wxService movieService = retrofit.create(wxService.class);
        WXToken token=null;
        movieService.getWXToken("client_credential", "wx146d06d152af3d07","6e4b87f16ab21f6eeaa56a9648a5dad4")
                .enqueue(new Callback<WXToken>() {
                    @Override
                    public void onResponse(Call<WXToken> call, Response<WXToken> response) {
                        Log.e("dxsTest","onResponse:");
                    }

                    @Override
                    public void onFailure(Call<WXToken> call, Throwable t) {

                    }
                });
    }

    private void sendMessage(final String openid,final String templiId,final WXToken token){
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        // Log信息拦截器
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);//这里可以选择拦截级别
        //设置 Debug Log 模式
        // init cookie manager
        if(cookieJar==null){
            cookieJar =
                    new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(MyApp.context));
        }
        builder.addInterceptor(loggingInterceptor)
                .cookieJar(cookieJar);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(builder.build())
                .build();

        RequestBody body=
                RequestBody.create(okhttp3.MediaType.parse("application/json"), "");

        wxService movieService = retrofit.create(wxService.class);
        movieService.sendMessage(token.getAccess_token(), body);
    }

    public void sendMessage(final String openid,final String templiId){
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        // Log信息拦截器
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);//这里可以选择拦截级别
        //设置 Debug Log 模式
        // init cookie manager
        if(cookieJar==null){
            cookieJar =
                    new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(MyApp.context));
        }
        builder.addInterceptor(loggingInterceptor)
                .cookieJar(cookieJar);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(builder.build())
                .build();

        wxService movieService = retrofit.create(wxService.class);
        movieService.sendMessageTest(openid, templiId).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.e("dxsTest",response.toString());
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("dxsTest",t.toString());
            }
        });
    }
}

