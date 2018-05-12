package com.mvp.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.user.notification.R;
import com.example.user.notification.entity.Ble;

public class BleControlActivity extends AppCompatActivity {

    private Ble ble;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ble_control);
        ble= (Ble) getIntent().getSerializableExtra(Ble.class.getSimpleName());
    }
}
