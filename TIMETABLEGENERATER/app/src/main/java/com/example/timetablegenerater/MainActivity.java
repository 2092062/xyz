package com.example.timetablegenerater;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText uname;
    private EditText pwd;
    private Button btn;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        uname = (EditText) findViewById(R.id.uid);
        tv=findViewById(R.id.tid);
        pwd = (EditText) findViewById(R.id.pid);


    }


    public void adminpage(View view) {

        String sname=uname.getText().toString();
        if(uname.getText().toString().equals("admin")){
            Intent intent = new Intent(MainActivity.this, AdminPage.class);
            startActivity(intent);
        }
        else{
            tv.setText("hmm");
        }

    }



}
