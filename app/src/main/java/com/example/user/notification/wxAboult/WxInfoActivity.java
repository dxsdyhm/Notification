package com.example.user.notification.wxAboult;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.example.user.notification.R;
import com.example.user.notification.gloable.MyApp;
import com.example.user.notification.gloable.Util;
import com.tencent.mm.opensdk.modelbiz.SubscribeMessage;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WxInfoActivity extends AppCompatActivity {


    @BindView(R.id.btn_send)
    Button btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wx_info);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_send)
    public void onViewClicked() {
        //"param":"openid=odPSpxDrlQ3qkUigUaTWncunkUII
        // &template_id=_dxyQ9KpIPEcLKUxuoQWg37HPGK46oMMR9WS7KGdUx4
        // &action=confirm
        // &reserved=dxsdyhm
        // &scene=1023"
        //6e4b87f16ab21f6eeaa56a9648a5dad4
        //9_uI9EyuO4YHsxaIQr7hsifih7EvFwctkLCZFlrH_KKoO_ivuGewaSup2cFFS9u891Wm2gmj0Wxm-oog1RAkAAei8rRRNsom_wxkMaGVD7bx6TtZJKoI09ZdylLmMDEIfAAALMQ
        SubscribeMessage.Req req = new SubscribeMessage.Req();
        req.scene = 1023;
        req.templateID = "_dxyQ9KpIPEcLKUxuoQWg37HPGK46oMMR9WS7KGdUx4";
        req.reserved ="dxsdyhm";
        MyApp.api.sendReq(req);
    }

    private String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }
}
