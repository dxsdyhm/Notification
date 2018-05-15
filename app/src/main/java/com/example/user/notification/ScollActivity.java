package com.example.user.notification;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.mvp.ui.widget.VoiceGroup;
import com.mvp.ui.widget.VoiceView;

public class ScollActivity extends AppCompatActivity {
    private VoiceView view;
    private VoiceGroup group;
    private boolean isPlaying=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoll);
        group=findViewById(R.id.vg_voice);
        group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isPlaying=!isPlaying;
                if(isPlaying){
                    ((VoiceGroup)v).startPlaying();
                }else {
                    ((VoiceGroup)v).stopPlaying();
                }
            }
        });
    }
}
