package com.example.arthur.chinesetree;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class SearchActivity extends AppCompatActivity {

    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        context = this;

        ImageView img0 = (ImageView)findViewById(R.id.img0);
        img0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 產生一個Intent, 用來轉換Activity
                Intent intent = new Intent();

                // 設定轉換的Activity
                intent.setClass(context, MainActivity.class);
                // 轉換至下一個Activity
                context.startActivity(intent);
            }
        });

        ImageView img2 = (ImageView)findViewById(R.id.img2);
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 產生一個Intent, 用來轉換Activity
                Intent intent = new Intent();

                // 設定轉換的Activity
                intent.setClass(context, PracticeActivity.class);
                // 轉換至下一個Activity
                context.startActivity(intent);
            }
        });
    }

}
