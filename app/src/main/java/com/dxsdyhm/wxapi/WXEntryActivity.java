package com.dxsdyhm.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.user.notification.gloable.MyApp;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelbiz.SubscribeMessage;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;

/**
 * Created by dxs on 2018/4/23.
 */

public class WXEntryActivity extends Activity implements IWXAPIEventHandler {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent=getIntent();
        intent.getStringExtra("");
        Log.e("dxsTest","intent1:"+getIntent().toString());
        initHandle();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.e("dxsTest","intent2:"+intent.toString());
        initHandle();
    }

    private void initHandle(){
        if(MyApp.api==null){
            MyApp.initWeiXi();
        }
        MyApp.api.handleIntent(getIntent(),this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        finish();
    }

    @Override
    public void onReq(BaseReq baseReq) {
        Log.e("dxsTest","baseReq:"+baseReq.toString());
    }

    @Override
    public void onResp(BaseResp baseResp) {
        Log.e("dxsTest","baseResp:"+baseResp.toString());
        if(baseResp.errCode==BaseResp.ErrCode.ERR_OK){
            if(baseResp instanceof SubscribeMessage.Resp){
                SubscribeMessage.Resp resp= (SubscribeMessage.Resp) baseResp;
                Log.e("dxsTest",resp.action);
                Log.e("dxsTest",resp.templateID);
                Log.e("dxsTest",resp.openId);
                Toast.makeText(this,"用户行为："+resp.action,Toast.LENGTH_LONG).show();
            }
        }
    }
}
