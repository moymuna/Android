package com.example.myaplication.session;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.myaplication.model.response.EmployeeResponse;
import com.example.myaplication.model.response.LogInResponse;
import com.google.gson.Gson;

public class SesssoinManager {
    private static final String PREF_NAME = "hrm_pref";

    private static final String TOKEN = "token";
    private static final String USER = "user";
    private static final String EMPLOYEE = "employee";

    private SharedPreferences preferences;

    private Gson gson = new Gson();

    public SesssoinManager(Context context){

        preferences =
                context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);

    }

    //=========================
    // TOKEN
    //=========================

    public void saveToken(String token){

        preferences.edit().putString(TOKEN,token).apply();

    }

    public String getToken(){

        return preferences.getString(TOKEN,null);

    }

    //=========================
    // USER
    //=========================

    public void saveUser(LogInResponse user){

        preferences.edit()
                .putString(USER,gson.toJson(user))
                .apply();

    }

    public LogInResponse getUser(){

        String json=preferences.getString(USER,null);

        if(json==null)
            return null;

        return gson.fromJson(json,LogInResponse.class);

    }

    //=========================
    // EMPLOYEE
    //=========================

    public void saveEmployee(EmployeeResponse employee){

        preferences.edit()
                .putString(EMPLOYEE,gson.toJson(employee))
                .apply();

    }

    public EmployeeResponse getEmployee(){

        String json=preferences.getString(EMPLOYEE,null);

        if(json==null)
            return null;

        return gson.fromJson(json,EmployeeResponse.class);

    }

    public boolean isLoggedInABoolean(){

        return getToken()!=null;

    }

    public void logout(){

        preferences.edit().clear().apply();

    }

}
