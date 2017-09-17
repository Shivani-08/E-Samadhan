package com.example.sakshi.esamadhan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.sakshi.esamadhan.utils.PrefsHelper;

public class MainActivity extends AppCompatActivity {


    TextView mTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        Thread timerThread = new Thread() {
            public void run() {
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    String token = (String) PrefsHelper.getPrefsHelper(MainActivity.this).getPref(PrefsHelper.PREF_TOKEN, "logout");
                    Log.e("tok ", token);
                    if (token.equals("logout")) {
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(intent);
                        MainActivity.this.finish();
                    } else {
                        Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                        startActivity(intent);
                        MainActivity.this.finish();

                    }
                }
            }
        };
        timerThread.start();
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        finish();
    }


}