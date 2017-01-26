package com.example.arthur.chinesetree;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

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
}
