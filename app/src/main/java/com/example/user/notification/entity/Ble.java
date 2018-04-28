package com.example.user.notification.entity;

import com.inuker.bluetooth.library.beacon.Beacon;

/**
 * Created by dxs on 2018/4/23.
 */

public class Ble {
    private String mac;
    private Beacon bean;

    public Ble() {
    }

    public Ble(String mac, Beacon bean) {
        this.mac = mac;
        this.bean = bean;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public Beacon getBean() {
        return bean;
    }

    public void setBean(Beacon bean) {
        this.bean = bean;
    }
}
