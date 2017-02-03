package com.example.arthur.chinesetree;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.arthur.chinesetree.async.MyAsyncTask;
import com.example.arthur.chinesetree.data.Common;

import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    Context context;

    Button btnSubmit;
    TextView txtShow;
    EditText txtInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }


    public void init() {
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        txtShow = (TextView) findViewById(R.id.txtShow);
        txtInput = (EditText) findViewById(R.id.txtInput);
    }

    public void btnSubmitClick(View view) {
        String editTextString = txtInput.getText().toString();
        txtShow.setText(editTextString);
    }

    protected void onResume() {
        super.onResume();

        //--------------------
        // 儲存目前執行環境
        //--------------------
        context = this;

        //--------------------------------------
        // 按鈕點擊後, 執行取出及顯示資料動作
        //---------OCR按鈕事件----------------//
        Button ocr = (Button) findViewById(R.id.btnOrc);
        ocr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 呼叩執行取出及顯示資料動作函式
                getAndDisplayData();
            }
        });
        //---------OCR按鈕事件----------------//
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //----------------呼叫與主機連線class並且解析json-----------------------------------//
    protected void getAndDisplayData() {
        //======================================================
        // (1)宣告一個處理資料取回後, 處理回傳JSON格式資料的物件.
        //======================================================
        MyAsyncTask myAsyncTask = new MyAsyncTask(context, new MyAsyncTask.TaskListener() {
            @Override
            public void onFinished(String result) {
                try {
                    if (result == null) {
                        Toast.makeText(context, "無資料!", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    //--------------------------------
                    // 將由主機取回的字串轉為JSONArray
                    //--------------------------------
                    JSONArray jsonArray = new JSONArray(result);

                    //-----------------------------------------------------------
                    // 將JSONArray內容生成HashMap物件, 再加入ArrayList物件中
                    //-----------------------------------------------------------
                    ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();

                    for (int i = 0; i < jsonArray.length(); i++) {
                        HashMap<String, String> item = new HashMap<>();
                        item.put("Name", jsonArray.getJSONObject(i).getString("Name"));
                        item.put("Add", jsonArray.getJSONObject(i).getString("Add"));
                        arrayList.add(item);
                    }

                    //------------------------------------------
                    // 準備一個ListView, 用來顯示ArrayList內容
                    //------------------------------------------
                    ListView listView = (ListView) findViewById(R.id.myListView);

                    //----------------------------------------------------
                    // 準備一個橋接資料及版型的Adapter物件
                    // 將arrayList中的Name及Add製成大標題及小標題
                    //----------------------------------------------------
                    SimpleAdapter simpleAdapter = new SimpleAdapter(
                            context,
                            arrayList,
                            android.R.layout.simple_list_item_2,
                            new String[]{"Name", "Add"},
                            new int[]{android.R.id.text1, android.R.id.text2}
                    );

                    //-----------------------------------
                    // 將ListView物件連上Adapter物件
                    //-----------------------------------
                    listView.setAdapter(simpleAdapter);
                } catch (Exception e) {
                    Toast.makeText(context, "連線失敗!", Toast.LENGTH_SHORT).show();
                }
            }
        });


        //=====================
        // (2)取得輸入之區段編號
        //=====================
        TextView segIdText = (TextView) findViewById(R.id.txtInput);
        String segId = segIdText.getText().toString().trim();


        //==================================================
        // (3)將網址及POST參數傳給主機, 向網站發出POST請求
        //==================================================
        myAsyncTask.execute(Common.url, segId);
    }
}
