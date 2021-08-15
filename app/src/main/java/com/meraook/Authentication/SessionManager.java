package com.meraook.Authentication;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

public class SessionManager {

    SharedPreferences userSession;
    SharedPreferences.Editor editor;
    Context context;

    private static final String IS_LOGIN = "IsLoggedIn";

    public static final String KEY_NAME = "name";
    public static final String KEY_MOBILE_NUMBER = "mobileNumber";

    public SessionManager(Context _context) {
        context = _context;
        userSession = context.getSharedPreferences("userLoginSession", Context.MODE_PRIVATE);
        editor = userSession.edit();
    }

    public void createLoginSession(String name, String mobileNumber) {

        editor.putBoolean(IS_LOGIN, true);

        editor.putString(KEY_NAME, name);
        editor.putString(KEY_MOBILE_NUMBER, mobileNumber);

        editor.commit();

    }

    public HashMap<String, String> getUsersDetailFromSession() {
        HashMap<String, String> userData = new HashMap<String, String>();

        userData.put(KEY_NAME, userSession.getString(KEY_NAME, null));
        userData.put(KEY_MOBILE_NUMBER, userSession.getString(KEY_MOBILE_NUMBER, null));

        return userData;
    }

    public boolean checkLogin() {

        if (userSession.getBoolean(IS_LOGIN, false)) {
            return true;
        } else
            return false;

    }

    public void logout() {
        editor.clear();
        editor.commit();
    }
}
