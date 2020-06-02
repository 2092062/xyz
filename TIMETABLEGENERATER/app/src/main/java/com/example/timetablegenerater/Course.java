package com.example.timetablegenerater;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Hashtable;



public class Course extends AppCompatActivity {
   private Hashtable<String,Integer> course= new Hashtable<String, Integer>();;
   private EditText cname;
   private EditText credits;
   private Context x=this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        cname=(EditText)findViewById(R.id.cname);
        credits=(EditText)findViewById(R.id.credits);
        course=CourseFile.readData(this);
    }
public void course(View v){
        String name=cname.getText().toString();
        String credit=credits.getText().toString();
        int y=Integer.parseInt(credit);
        course.put(name,y);
        CourseFile.writeData(course,this);
        cname.setText(" ");
        credits.setText(" ");
}

}
