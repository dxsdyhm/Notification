package com.example.user.notification.wxAboult.wxservice;

import com.example.user.notification.wxAboult.wxservice.entry.WXToken;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by dxs on 2018/5/3.
 */

public interface wxService {
//    https请求方式: GET
//    https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET
    @GET("cgi-bin/token")
    Call<WXToken> getWXToken(@Query("grant_type") String grant_type, @Query("appid") String appid, @Query("secret") String secret);

//    http请求方式: post
//    https://api.weixin.qq.com/cgi-bin/message/template/subscribe?access_token=ACCESS_TOKEN
    @POST("message/template/subscribe")
    Call<Object> sendMessage(@Query("access_token") String access_token,@Body RequestBody route);

    @FormUrlEncoded
    @POST("wxOpenTest")
    Call<ResponseBody> sendMessageTest(@Field("openid") String open_id, @Field("templeteid") String templete_id);

}
