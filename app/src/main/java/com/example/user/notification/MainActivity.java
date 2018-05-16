package com.example.user.notification;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.mvp.ui.activity.BleListActivity;
import com.example.user.notification.wxAboult.WxInfoActivity;
import com.example.user.notification.wxAboult.WxLoginActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = "chat";
            String channelName = "聊天消息";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            createNotificationChannel(channelId, channelName, importance);

            channelId = "subscribe";
            channelName = "订阅消息";
            importance = NotificationManager.IMPORTANCE_DEFAULT;
            createNotificationChannel(channelId, channelName, importance);
        }

        initUi();
    }

    private void initUi() {
        Button btnChat = findViewById(R.id.btn_sendchat);
        Button btnObser = findViewById(R.id.btn_send_observer);
        Button btnWxLogin = findViewById(R.id.btn_wxlogin);
        btnChat.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.DONUT)
            @Override
            public void onClick(View v) {
                sendChatMsg(v);
                Intent to=new Intent();
                to.setClass(MainActivity.this,BleListActivity.class);
                Log.e("dxsTest","BleListActivity");
                startActivity(to);
            }
        });
        btnObser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendSubscribeMsg(v);
                toWxAboult();
            }
        });

        btnWxLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toWxLogin();
            }
        });

        findViewById(R.id.btn_voice).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testVoice();
            }
        });

        findViewById(R.id.btn_device).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toSlide();
            }
        });
    }

    private void toWxAboult(){
        Intent to=new Intent();
        to.setClass(MainActivity.this,WxInfoActivity.class);
        startActivity(to);
    }

    private void toWxLogin(){
        Intent to=new Intent();
        to.setClass(MainActivity.this,WxLoginActivity.class);
        startActivity(to);
    }

    private void toSlide(){
        Intent to=new Intent();
        to.setClass(MainActivity.this,SlideActivity.class);
        startActivity(to);
    }


    public void sendChatMsg(View view) {
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification notification = new NotificationCompat.Builder(this, "chat")
                .setContentTitle("收到一条聊天消息")
                .setContentText("今天中午吃什么？")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher_background))
                .setAutoCancel(true)
                .build();
        manager.notify(1, notification);
    }

    public void sendSubscribeMsg(View view) {
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification notification = new NotificationCompat.Builder(this, "subscribe")
                .setContentTitle("收到一条订阅消息")
                .setContentText("地铁沿线30万商铺抢购中！")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher_foreground))
                .setAutoCancel(true)
                .build();
        manager.notify(2, notification);
    }

    @TargetApi(Build.VERSION_CODES.O)
    private void createNotificationChannel(String channelId, String channelName, int importance) {
        NotificationChannel channel = new NotificationChannel(channelId, channelName, importance);
        NotificationManager notificationManager = (NotificationManager) getSystemService(
                NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(channel);
    }

    private void testVoice(){
        Intent to=new Intent();
        to.setClass(MainActivity.this,ScollActivity.class);
        startActivity(to);
    }
}
