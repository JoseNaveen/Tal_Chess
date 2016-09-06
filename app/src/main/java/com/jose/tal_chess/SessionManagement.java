package com.jose.tal_chess;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

/**
 * Created by joalphon on 03-Sep-16.
 */
public class SessionManagement {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;
    int MODE = 0;
    private static final String PREF = "LOGIN";
    private static final String IS_LOGIN = "false";
    public static final String KEY_NAME = "name";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_EMAIL = "email";
    public SessionManagement(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF,MODE);
        editor = pref.edit();

    }
    public void createLoginSession(String name,String email){
        editor.putBoolean(IS_LOGIN,true);
        editor.putString(KEY_NAME,name);
        editor.putString(KEY_EMAIL,email);
        editor.commit();
    }
    public void checkLogin(){
        if(!this.isLoggedIn()){
            Intent i = new Intent(_context,SigninActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            _context.startActivity(i);
        }
    }
    public void logoutUser(){
        //Toast.makeText(_context,"Signing Out",Toast.LENGTH_LONG).show();
        editor.clear();
        editor.commit();
        Intent i = new Intent(_context,MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        _context.startActivity(i);
    }
    public boolean isLoggedIn(){
        //Toast.makeText(_context,"Checking log in status",Toast.LENGTH_LONG).show();
        return(pref.getBoolean(IS_LOGIN,false));
    }
}
