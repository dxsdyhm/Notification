package com.example.user.notification.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.notification.R;
import com.example.user.notification.entity.Ble;
import com.inuker.bluetooth.library.beacon.Beacon;

import me.drakeet.multitype.ItemViewBinder;

/**
 * Created by dxs on 2018/4/23.
 */

public class BlebindView extends ItemViewBinder<Ble,BlebindView.ViewHolder> {

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.item_rc_ble, parent, false);
        return new BlebindView.ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull Ble item) {
        holder.mac.setText(item.getMac());
        holder.content.setText(item.getBean().toString());
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @NonNull private final TextView mac;
        private final TextView content;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.mac =itemView.findViewById(R.id.tx_ble_mac);
            this.content=itemView.findViewById(R.id.tx_ble_name);
        }
    }

}
