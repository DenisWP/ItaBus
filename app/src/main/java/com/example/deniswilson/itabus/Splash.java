package com.example.deniswilson.itabus;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;


/**
 * Created by Denis Wilson on 17/11/2016.
 */

public class Splash extends AppCompatActivity implements Runnable{

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*Remove o título da activity que pertence a ela*/
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.splash);
        Handler handler = new Handler();
        handler.postDelayed(this, 4000);

    }

    /**/
    @Override
    public void run(){
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
