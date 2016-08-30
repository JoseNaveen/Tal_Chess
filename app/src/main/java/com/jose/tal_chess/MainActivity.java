package com.jose.tal_chess;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void authenticateUser(View view) {
        EditText username = (EditText) findViewById(R.id.username);
        EditText password = (EditText) findViewById(R.id.password);
        String uname = username.getText().toString();
        String pass = password.getText().toString();

        if (username.getText().toString() != "") {
            LoginWS loginws = new LoginWS(MainActivity.this);
            loginws.execute(uname,pass);
        }

    }
    private class LoginWS extends AsyncTask<String,Void,String>{
        private MainActivity activity;
        public LoginWS(MainActivity mainActivity){
            this.activity = mainActivity;
        }
        @Override
        protected String doInBackground(String... params) {
            String result = "Success";
            try{
                login(params[0],params[1],activity);
                //return (login());
            } catch (Exception e){
                e.printStackTrace();
            }

            return (result);

        }

    }
    public void login(String username, String password, final MainActivity activity) throws Exception {
        OkHttpClient client = new OkHttpClient();
        RequestBody formBody = new FormBody.Builder()
                .add("username",username)
                .add("password",password)
                .build();
        Request request = new Request.Builder()
                .url("http://192.168.1.3:3000/signin")
                .post(formBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected code " + response);
                } else {
                    JSONObject parser = null;
                    try {
                        parser = new JSONObject(response.body().string());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    String result = null;
                    try {
                        result = parser.get("result").toString();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    if (result.equals("success")) {
                        startActivity(new Intent(activity, Home.class));
                    } else {


                    }
                }
            }


        });
    }
}
