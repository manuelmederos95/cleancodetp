package com.esgi.test.tppercistance;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    public static final String MY_PREF = "my_pref";
    public static final String MY_DATE_KEY = "MY_DATE";
    public static final String MY_CACHE_TXT = "MY_CACHE_TXT";
    public static final String MY_FILE_TXT = "MY_FILE_TXT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void savePreferences(View view){
        SharedPreferences sharedPreferences = //getPreferences(MODE_APPEND); // Pref. per Activity
            getSharedPreferences(MY_PREF, MODE_APPEND);

        long date = new Date().getTime();
        sharedPreferences.edit().putLong(MY_DATE_KEY,date).commit();
    }

    public void startSecondActivity(View view) {
        startActivity(new Intent(this, SecondActivity.class));
    }

    public void saveCache(View view){
        File cacheDir = getCacheDir();
        File cacheFile = new File(cacheDir, MY_CACHE_TXT);
        try {
            FileOutputStream fos = new FileOutputStream(cacheFile, true);
            String message = "Le cache se cache\n";
            writeToFileOutputStream(fos, message);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    private void writeToFileOutputStream(FileOutputStream fos, String message) throws IOException {
        fos.write(message.getBytes());
        fos.flush();
        fos.close();
    }

    public void saveFile(View view){
        try{
            FileOutputStream fos = openFileOutput(MY_FILE_TXT,MODE_APPEND);
            String message = "Le fichier fit chier \n";
            writeToFileOutputStream(fos,message);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void saveDatabase(View view){
        Student student  = new Student();
        student.setLastName("MASSIN");
        student.setFirstName("Anthony");

        ContentValues insertRow = new ContentValues();
        insertRow.put(Student.COL_LAST_NAME,student.getLastName());
        insertRow.put(Student.COL_FIRST_NAME,student.getFirstName());
        long idInserted = db.insert(Student.TABLE_NAME,nullColumnHack:null,insertRow);
        student.setId(idInserted);
        Log.i(tag:"INSERT", student.toString());

        //UPDATE
        ContentValues updateRow = new ContentValues();
        updateRow.put(Student.COL_LAST_NAME, "XEXENO");
        String[] updateWhereArgs = {"MASSIN"};
        db.update(Student.TABLE_NAME,Student.COL_LAST_NAME+ " =?" );

    }

}
