package com.example.user.notification.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.user.notification.R;
import com.example.user.notification.adapter.BlebindView;
import com.example.user.notification.entity.Ble;
import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.beacon.Beacon;
import com.inuker.bluetooth.library.search.SearchRequest;
import com.inuker.bluetooth.library.search.SearchResult;
import com.inuker.bluetooth.library.search.response.SearchResponse;
import com.inuker.bluetooth.library.utils.BluetoothLog;

import java.util.HashMap;
import java.util.Map;

import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

public class BleListActivity extends AppCompatActivity {

    private BluetoothClient mClient;
    private Map<String,Ble> bles=new HashMap<>();
    private MultiTypeAdapter adapter;

    /* Items 等同于 ArrayList<Object> */
    private Items items;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ble_list);

        mClient= new BluetoothClient(this);
        scanBle();
        initUI();
    }

    private void initUI() {
        recyclerView= findViewById(R.id.rc_ble);
        adapter = new MultiTypeAdapter();

        /* 注册类型和 View 的对应关系 */
        adapter.register(Ble.class, new BlebindView());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        items = new Items();
        adapter.setItems(items);
    }

    private void scanBle() {
        SearchRequest request = new SearchRequest.Builder()
                .searchBluetoothLeDevice(3000, 3)   // 先扫BLE设备3次，每次3s
                .searchBluetoothClassicDevice(5000) // 再扫经典蓝牙5s
                .searchBluetoothLeDevice(2000)      // 再扫BLE设备2s
                .build();

        mClient.search(request, new SearchResponse() {
            @Override
            public void onSearchStarted() {

            }

            @Override
            public void onDeviceFounded(SearchResult device) {
                Beacon beacon = new Beacon(device.scanRecord);
                BluetoothLog.v(String.format("beacon for %s\n%s", device.getAddress(), beacon.toString()));
                Ble ble=new Ble(device.getAddress(),beacon);
                bles.put(ble.getMac(),ble);
            }

            @Override
            public void onSearchStopped() {
                items.clear();
                items.addAll(bles.values());
                Log.e("dxsTest","items:"+items.size());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onSearchCanceled() {

            }
        });
    }
}
