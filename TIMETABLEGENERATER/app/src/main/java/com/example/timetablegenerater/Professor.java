package com.example.timetablegenerater;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Professor extends AppCompatActivity  {
    Spinner spn;
    EditText pname;
    List<String> course=new ArrayList<>();
    ArrayAdapter<String> spnadap;
    Hashtable<String,Integer> CourseData=new Hashtable<String, Integer>();
    Hashtable<String,String> ProfData=new Hashtable<String, String>();
    String prof;
    String scourse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor);
        spn=findViewById(R.id.spn);
        pname=findViewById(R.id.pname);

        CourseData=CourseFile.readData(this);
        ProfData=ProfessorFile.readData(this);
        Set<String> keys=CourseData.keySet();
        Iterator<String> itr=keys.iterator();
        while(itr.hasNext()){
           String str=itr.next();
            course.add(str);

        }
        spnadap=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,course);
        spnadap.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn.setAdapter(spnadap);
       spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               scourse=course.get(position);
           }

           @Override
           public void onNothingSelected(AdapterView<?> parent) {

           }
       });



    }
    public void fprof(View v){
        prof=pname.getText().toString();
        ProfData.put(prof,scourse);
        ProfessorFile.writeData(ProfData,this);
    }



}
