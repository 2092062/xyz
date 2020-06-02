package com.example.timetablegenerater;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Delete extends AppCompatActivity {
    Spinner spn1;
    Spinner spn2;
    Spinner spn3;
    ArrayAdapter<String> spnadp1;
    ArrayAdapter<String> spnadp2;
    ArrayAdapter<String> spnadp3;
    Hashtable<String,Integer> CourseData=new Hashtable<>();
    Hashtable<String,String> ProfData=new Hashtable<>();
    List<ArrayList<String>>  BatchData=new ArrayList<ArrayList<String>>() ;
    List<String> course=new ArrayList<>();
    List<String> professor=new ArrayList<>();
    List<String> batch1=new ArrayList<>();
    List<String> batch2=new ArrayList<>();
    int cpos;
    int ppos;
    int  bpos;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        spn1=findViewById(R.id.scourse);
        spn2=findViewById(R.id.sprof);
        spn3=findViewById(R.id.sbatch);
        CourseData=CourseFile.readData(this);
        ProfData=ProfessorFile.readData(this);
        BatchData=BatchFile.readData(this);
        Set<String> keys=CourseData.keySet();
        Iterator<String> itr=keys.iterator();
        while(itr.hasNext()){
            String str=itr.next();
            course.add(str);

        }
        Set<String> key=ProfData.keySet();
        Iterator<String> itr1=key.iterator();
        while(itr1.hasNext()){
            String str1=itr1.next();
            professor.add(str1);

        }
        for(int i=0;i<BatchData.size();i++){
            batch1=BatchData.get(i);
            batch2.add(batch1.get(0));
        }

        spnadp1=new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,course);
        spnadp1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn1.setAdapter(spnadp1);
        spnadp2=new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,professor);
        spnadp2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn2.setAdapter(spnadp2);
        spnadp3=new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,batch2);
        spnadp3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn3.setAdapter(spnadp3);
        spn1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cpos=position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spn2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ppos=position;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spn3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                bpos=position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void dcourse(View v){
        CourseData.remove(course.get(cpos));
        course.remove(cpos);
        CourseFile.writeData(CourseData,this);
    }
    public void dprof(View v){
        ProfData.remove(professor.get(ppos));
        professor.remove(ppos);
        ProfessorFile.writeData(ProfData,this);
    }
    public void dbatch(View v){
        for(int i=0;i<BatchData.size();i++){
            if(BatchData.get(i).get(0)==batch2.get(bpos)){
                BatchData.remove(i);


            }

            BatchFile.writeData(BatchData,this);
        }
    }
}
