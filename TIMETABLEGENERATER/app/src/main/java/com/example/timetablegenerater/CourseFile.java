package com.example.timetablegenerater;


import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Hashtable;

public class CourseFile {
    private static final String name="cfile";
    public static void writeData(Hashtable<String, Integer> course, Context context){
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
    public static Hashtable<String,Integer> readData(Context context){
        Hashtable<String,Integer> course=null;
        try {
            FileInputStream is=context.openFileInput(name);
            ObjectInputStream ois=new ObjectInputStream(is);
            course=(Hashtable<String,Integer>)ois.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            course=new Hashtable<>();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return course;
    }
}
