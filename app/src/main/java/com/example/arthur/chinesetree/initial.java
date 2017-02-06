package com.example.arthur.chinesetree;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class initial extends AppCompatActivity {

    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial);
        context=this;
        new CountDownTimer(3000,1000){

            @Override
            public void onFinish() {
                // 產生一個Intent, 用來轉換Activity
                Intent intent=new Intent();

                // 設定轉換的Activity
                intent.setClass(context, MainActivity.class);

                // 轉換至下一個Activity
                context.startActivity(intent);
            }

            @Override
            public void onTick(long millisUntilFinished) {

            }

        }.start();
    }
}
