package com.example.timetablegenerater;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class AdminPage extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Button crb;
  TableRow tr;
  TableRow tr1;
  TableRow tr2;
    TableRow tr3;
  TableRow tr4;
  Spinner spn;
  Spinner spn2;
  List<View> view1=new ArrayList<>();
  Context con=this;

   Hashtable<String,Integer> CourseData=new Hashtable<>();
   Hashtable<String,String> ProfesserData=new Hashtable<>();
   List<ArrayList<String>> BatchData=new ArrayList<ArrayList<String>>();
    List<String> batch1=new ArrayList<>();
    List<String> batch2=new ArrayList<>();
    List<String> professor=new ArrayList<>();
    int[][] ar=new int[10][10];
    Random rand=new Random();
    ArrayAdapter<String> spnadp1;
    ArrayAdapter<String> spnadp2;
     TableRow row ;
     int pos;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);
        crb=findViewById(R.id.crb);
        tr1=findViewById(R.id.row1);
        tr2=findViewById(R.id.row2);
        tr4=findViewById(R.id.row4);
        tr3=findViewById(R.id.row3);

        view1.add(tr1);
        view1.add(tr2);
        view1.add(tr3);
        view1.add(tr4);
        spn=findViewById(R.id.bspn);


        CourseData=CourseFile.readData(this);
        ProfesserData=ProfessorFile.readData(this);
        BatchData=BatchFile.readData(this);
        for(int i=0;i<BatchData.size();i++){
            batch1=BatchData.get(i);
            batch2.add(batch1.get(0));
        }

        spnadp1=new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,batch2);
        spnadp1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn.setAdapter(spnadp1);
        Set<String> key=ProfesserData.keySet();
        Iterator<String> itr1=key.iterator();
        while(itr1.hasNext()){
            String str1=itr1.next();
            professor.add(str1);

        }

        for(int j = 0; j < tr1. getChildCount(); j++){
            TextView check=(TextView)tr1.getChildAt(j);
            if(check.getText().toString().equals("")){
                ar[0][j]=0;

            }
            else{
                ar[0][j]=1;
            }
        }
        for(int j = 0; j < tr1. getChildCount(); j++){
            TextView check=(TextView)tr1.getChildAt(j);
            if(check.getText().toString()==""){
                ar[0][j]=0;

            }
            else{
                ar[0][j]=1;
            }
        }
        for(int j = 0; j < tr2. getChildCount(); j++){
            TextView check=(TextView)tr1.getChildAt(j);
            if(check.getText().toString().equals("")){
                ar[1][j]=0;

            }
            else{
                ar[1][j]=1;
            }
        }
        for(int j = 0; j < tr3. getChildCount(); j++){
            TextView check=(TextView)tr1.getChildAt(j);
            if(check.getText().toString()==""){
                ar[2][j]=0;

            }
            else{
                ar[2][j]=1;
            }
        }
        for(int j = 0; j < tr4. getChildCount(); j++){
            TextView check=(TextView)tr1.getChildAt(j);
            if(check.getText().toString().equals("")){
                ar[3][j]=0;

            }
            else{
                ar[3][j]=1;
            }
        }


    }
    public void course(View v){
        Intent intent=new Intent(AdminPage.this,Course.class);
        startActivity(intent);
    }
   public  void prof(View v){
       Intent intent=new Intent(AdminPage.this,Professor.class);
       startActivity(intent);
   }
   public void batch(View v){
       Intent intent=new Intent(AdminPage.this,Batch.class);
       startActivity(intent);
   }
   public void delete(View v){
       Intent intent=new Intent(AdminPage.this,Delete.class);
       startActivity(intent);

   }

    public void add(View v){
        ArrayList<Integer> hmm=new ArrayList<Integer>();
        for(int i=0;i<3;i++){
            for(int j=0;j<4;j++){
                if(ar[i][j]==0){
                    int hmm2=i*10+j;
                    hmm.add(hmm2);
                }
            }
        }
        for(int i=0;i<BatchData.size();i++) {

            if (BatchData.get(i).get(0).equals(batch2.get(pos)) ) {
                int credits = CourseData.get(BatchData.get(i).get(1));
                for (int j = 1; j <= credits; j++) {
                    int hmm3=rand.nextInt(hmm.size());
                    int hmm4=hmm.get(hmm3)%10;
                    int hmm5=hmm.get(hmm3)/10;

                    String ans = BatchData.get(i).get(1)+" "+ BatchData.get(i).get(2);
                    TextView txt2=new TextView(this);
                    txt2.setText(ans);
                    row=(TableRow)view1.get(hmm4);
                    row.addView(txt2,hmm5);
                    ar[hmm5][hmm4]=1;
                }
            }
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        pos=position;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
