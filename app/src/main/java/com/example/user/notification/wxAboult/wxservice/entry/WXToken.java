package com.example.user.notification.wxAboult.wxservice.entry;

import java.io.Serializable;

/**
 * Created by dxs on 2018/5/3.
 */

public class WXToken implements Serializable{
//    {"access_token":"ACCESS_TOKEN","expires_in":7200}
    private String access_token;
    private int expires_in;

    public WXToken() {
    }

    public WXToken(String access_token, int expires_in) {
        this.access_token = access_token;
        this.expires_in = expires_in;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    @Override
    public String toString() {
        return "WXToken{" +
                "access_token='" + access_token + '\'' +
                ", expires_in=" + expires_in +
                '}';
    }
}
