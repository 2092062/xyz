package com.example.timetablegenerater;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class BatchFile {
    private static final String name="bfile";
    public static void writeData(List<ArrayList<String>> course, Context context){
        try {
            FileOutputStream os=context.openFileOutput(name,Context.MODE_PRIVATE);
            ObjectOutputStream fos=new ObjectOutputStream(os);
            fos.writeObject(course);
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static List<ArrayList<String>> readData(Context context){
        List<ArrayList<String>> course=null;
        try {
            FileInputStream is=context.openFileInput(name);
            ObjectInputStream ois=new ObjectInputStream(is);
            course=(List<ArrayList<String>>)ois.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            course=new ArrayList<ArrayList<String>>();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return course;
    }
}
