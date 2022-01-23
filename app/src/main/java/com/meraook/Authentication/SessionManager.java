package com.meraook.Authentication;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

public class SessionManager {

    Context context;
    SharedPreferences sharedPreferences;

    private String mobile_number;
    private String name;

    public SessionManager(Context context){
        this.context=context;
        sharedPreferences=context.getSharedPreferences("userinfo",Context.MODE_PRIVATE);

    }
    public String getMobile_number() {
        mobile_number=sharedPreferences.getString("userdata","");
        return mobile_number;
    }

    public void setMobile_number(String mobile_number) {
        this.mobile_number = mobile_number;
        sharedPreferences.edit().putString("userdata",mobile_number).commit();
    }


    public void removeUser(){
        sharedPreferences.edit().clear().commit();
    }


}
