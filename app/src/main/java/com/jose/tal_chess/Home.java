package com.jose.tal_chess;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Home extends AppCompatActivity {
    SessionManagement session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }
    public void logoutUser(View view){
        session = new SessionManagement(getApplicationContext());
        session.logoutUser();

    }
    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
    public void showBoard(View view){
        setContentView(R.layout.activity_board);
    }
}
