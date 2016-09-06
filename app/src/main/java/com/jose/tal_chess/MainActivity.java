package com.jose.tal_chess;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    SessionManagement session;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private EditText inputEmail, inputPassword;
    private TextInputLayout inputLayoutName, inputLayoutEmail, inputLayoutPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        session = new SessionManagement(getApplicationContext());









        if(session.isLoggedIn()){
            //Toast.makeText(getApplicationContext(),"Session is logged in already",Toast.LENGTH_LONG).show();
            Intent i = new Intent(getApplicationContext(),Home.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            //i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
            finish();
        }
        else{
            //Toast.makeText(getApplicationContext(),"Not logged in",Toast.LENGTH_LONG).show();
            setContentView(R.layout.tabbedlogin);
            toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            getSupportActionBar().setDisplayHomeAsUpEnabled(false);

            viewPager = (ViewPager) findViewById(R.id.viewpager);
            setupViewPager(viewPager);
            tabLayout = (TabLayout) findViewById(R.id.tabs);
            tabLayout.setupWithViewPager(viewPager);
            inputLayoutEmail = (TextInputLayout) findViewById(R.id.input_layout_email);
            inputLayoutPassword = (TextInputLayout) findViewById(R.id.input_layout_password);
            inputEmail = (TextInputEditText) findViewById(R.id.input_email);
            inputPassword = (TextInputEditText) findViewById(R.id.input_password);
        }

    }
    private void setupViewPager(ViewPager viewPager){
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new OneFragment(), "SIGN IN");
        adapter.addFragment(new TwoFragment(), "SIGN UP");
        //adapter.addFragment(new ThreeFragment(), "THREE");
        viewPager.setAdapter(adapter);
    }
    class ViewPagerAdapter extends FragmentPagerAdapter{
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
    public void authenticateUser(View view) {
        //Toast.makeText(getApplicationContext(),"Button pressed",Toast.LENGTH_LONG).show();
        TextInputEditText username = (TextInputEditText) findViewById(R.id.input_email);
        TextInputEditText password = (TextInputEditText) findViewById(R.id.input_password);
        String uname = username.getText().toString();
        String pass = password.getText().toString();

        if (username.getText().toString() != "") {
            //LoginWS loginws = new LoginWS(MainActivity.this);
            //loginws.execute(uname,pass);
            lin(uname,pass);
        }

    }
    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
    public void lin(String username,String password){
        session.createLoginSession(username,password);
        Toast.makeText(getApplicationContext(),"Trying to Log In",Toast.LENGTH_LONG).show();
        Intent home = new Intent(MainActivity.this,Home.class);
        home.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(home);
        finish();
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
    public void login(final String username, final String password, final MainActivity activity) throws Exception {
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
                        session.createLoginSession(username,password);
                        startActivity(new Intent(activity, Home.class));
                    } else {


                    }
                }
            }


        });
    }
    private boolean validateEmail() {
        String email = inputEmail.getText().toString().trim();

        if (email.isEmpty() || !isValidEmail(email)) {
            inputLayoutEmail.setError(getString(R.string.error_invalid_email));
            requestFocus(inputEmail);
            return false;
        } else {
            inputLayoutEmail.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validatePassword() {
        if (inputPassword.getText().toString().trim().isEmpty()) {
            inputLayoutPassword.setError(getString(R.string.error_invalid_password));
            requestFocus(inputPassword);
            return false;
        } else {
            inputLayoutPassword.setErrorEnabled(false);
        }

        return true;
    }

    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }
}
