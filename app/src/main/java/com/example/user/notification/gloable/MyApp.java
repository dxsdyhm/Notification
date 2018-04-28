package com.example.user.notification.gloable;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * Created by dxs on 2018/4/18.
 */

public class MyApp extends Application {
    private static String APPID_WEIXIN="wx146d06d152af3d07";
    public static IWXAPI api;
    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context=this;
        initWeiXi();
    }

    public static void initWeiXi() {
        api= WXAPIFactory.createWXAPI(context,APPID_WEIXIN,false);
        boolean regist= api.registerApp(APPID_WEIXIN);
        Log.e("dxsTest","regist:"+regist);
    }
}
