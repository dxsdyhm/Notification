package com.example.user.notification;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.notification.adapter.BlebindView;
import com.example.user.notification.adapter.DeviceBindView;
import com.example.user.notification.entity.Ble;
import com.example.user.notification.entity.Device;
import com.inuker.bluetooth.library.beacon.Beacon;
import com.mvp.ui.widget.OritaView;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

public class SlideActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "DemoActivity";

    private SlidingUpPanelLayout mLayout;

    private RecyclerView rlTest;
    private MultiTypeAdapter adapter;

    private Items items;

    private TextView t;
    private LinearLayoutManager linearLayoutManager;
    private GridLayoutManager gridLayoutManager;

    private Button btnAdd,btnDelete;
    private OritaView oritaView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide);

        getSupportActionBar().setElevation(0);

        linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        gridLayoutManager=new GridLayoutManager(this,3);
        //setSupportActionBar((Toolbar) findViewById(R.id.main_toolbar));
        rlTest=findViewById(R.id.list);
        adapter = new MultiTypeAdapter();

        /* 注册类型和 View 的对应关系 */
        adapter.register(Device.class, new DeviceBindView());
        rlTest.setLayoutManager(linearLayoutManager);
        rlTest.setAdapter(adapter);

        items = new Items();
        setData();
        adapter.setItems(items);
        adapter.notifyDataSetChanged();

        btnAdd=findViewById(R.id.btn_add);
        btnDelete=findViewById(R.id.btn_delete);
        oritaView=findViewById(R.id.vg_oritation);

        btnAdd.setOnClickListener(this);
        btnDelete.setOnClickListener(this);

        mLayout = findViewById(R.id.sliding_layout);
        mLayout.addPanelSlideListener(new SlidingUpPanelLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) {
                Log.i(TAG, "onPanelSlide, offset " + slideOffset);
                oritaView.setShowProgress(slideOffset);
            }

            @Override
            public void onPanelStateChanged(View panel, SlidingUpPanelLayout.PanelState previousState, SlidingUpPanelLayout.PanelState newState) {
                Log.i(TAG, "onPanelStateChanged " + newState);
                if(newState==SlidingUpPanelLayout.PanelState.COLLAPSED){
                    //线性
                    rlTest.setLayoutManager(linearLayoutManager);
                }else if(newState==SlidingUpPanelLayout.PanelState.EXPANDED){
                    //表格
                    rlTest.setLayoutManager(gridLayoutManager);
                }
            }
        });
        mLayout.setFadeOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
            }
        });


    }

    private void setData(){
        byte[] re=new byte[13];
        for(int i=0;i<10;i++){
            Device device=new Device(String.valueOf(i));
            items.add(device);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.demo, menu);
        MenuItem item = menu.findItem(R.id.action_toggle);
        if (mLayout != null) {
            if (mLayout.getPanelState() == SlidingUpPanelLayout.PanelState.HIDDEN) {
                item.setTitle(R.string.action_show);
            } else {
                item.setTitle(R.string.action_hide);
            }
        }
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_toggle: {
                if (mLayout != null) {
                    if (mLayout.getPanelState() != SlidingUpPanelLayout.PanelState.HIDDEN) {
                        mLayout.setPanelState(SlidingUpPanelLayout.PanelState.HIDDEN);
                        item.setTitle(R.string.action_show);
                    } else {
                        mLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
                        item.setTitle(R.string.action_hide);
                    }
                }
                return true;
            }
            case R.id.action_anchor: {
                if (mLayout != null) {
                    if (mLayout.getAnchorPoint() == 1.0f) {
                        mLayout.setAnchorPoint(0.7f);
                        mLayout.setPanelState(SlidingUpPanelLayout.PanelState.ANCHORED);
                        item.setTitle(R.string.action_anchor_disable);
                    } else {
                        mLayout.setAnchorPoint(1.0f);
                        mLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
                        item.setTitle(R.string.action_anchor_enable);
                    }
                }
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (mLayout != null &&
                (mLayout.getPanelState() == SlidingUpPanelLayout.PanelState.EXPANDED || mLayout.getPanelState() == SlidingUpPanelLayout.PanelState.ANCHORED)) {
            mLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_add:
                items.add(new Device(String.valueOf(items.size())));
                adapter.notifyDataSetChanged();
                break;
            case R.id.btn_delete:
                items.remove(0);
                adapter.notifyDataSetChanged();
                break;
        }
    }
}
