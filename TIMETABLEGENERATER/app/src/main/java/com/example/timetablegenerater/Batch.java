package com.example.timetablegenerater;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Batch extends AppCompatActivity {
    EditText bname;
    Spinner spn1;
    Spinner spn2;
    Hashtable<String,Integer> CourseData=new Hashtable<>();
    Hashtable<String,String> ProfessorData=new Hashtable<>();
    ArrayAdapter<String> spnadp;
    ArrayAdapter<String> spnadp1;
    List<String> course=new ArrayList<>();
    List<String> professor=new ArrayList<>();
    String cname;
    String pname;
    String batchname;
    List<ArrayList<String>> listoflist=new ArrayList<ArrayList<String>>() ;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_batch);
        bname=findViewById(R.id.eid);
        spn1=findViewById(R.id.spn1);
        spn2=findViewById(R.id.spn2);
        CourseData=CourseFile.readData(this);
        ProfessorData=ProfessorFile.readData(this);
        listoflist=BatchFile.readData(this);
        Set<String> keys=CourseData.keySet();
        Iterator<String> itr=keys.iterator();
        while(itr.hasNext()){
            String str=itr.next();
            course.add(str);

        }
        Set<String> key=ProfessorData.keySet();
        Iterator<String> itr1=key.iterator();
        while(itr1.hasNext()){
            String str1=itr1.next();
            professor.add(str1);

        }
        spnadp=new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,course);
        spnadp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn1.setAdapter(spnadp);
        spnadp1=new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,professor);
        spnadp1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn2.setAdapter(spnadp1);
        spn1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cname=course.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spn2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pname=professor.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
    public void batch(View v){
        batchname=bname.getText().toString();
        ArrayList<String> list=new ArrayList<String>();
        list.add(batchname);
        list.add(cname);
        list.add(pname);
        listoflist.add(list);
        BatchFile.writeData(listoflist,this);
    }
}
