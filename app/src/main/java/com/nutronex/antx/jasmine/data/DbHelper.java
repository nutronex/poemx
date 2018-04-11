package com.nutronex.antx.jasmine.data;

/**
 * Created by ak on 11/10/17.
 */

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.database.SQLException;
import com.nutronex.antx.jasmine.util.Common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLData;
import java.util.ArrayList;


public class DbHelper extends SQLiteOpenHelper {
    public Context context;

    public DbHelper(Context c){
        super(c,Common.DB_FILE.toString(),null,1);
        this.context = c;
        this.getReadableDatabase();
    }




    public boolean checkDB(){
        if((new File(getDbPath()).exists())) {
    //       Common.prn("-----file is already exist ");


            boolean val = false;
            SQLiteDatabase db = null;
            try {
                db = SQLiteDatabase.openDatabase(getDbPath(), null, SQLiteDatabase.OPEN_READONLY);
                val = db == null ? false : true;
            } catch (SQLException e) {
                Common.prn("checkDB - no db ");
                Common.prn(e.toString());
            }
            if (db != null) {
                db.close();
            }
            return val;
        }
        return false;
    }




    String getDbPath(){
        return context.getFilesDir()+"/"+Common.DB_FILE;
        //   return getWritableDatabase().getPath().toString();
    }



    public SQLiteDatabase getDBWritable(){
        SQLiteDatabase db = null;
        try{
            db= SQLiteDatabase.openDatabase(getDbPath(),null,SQLiteDatabase.OPEN_READWRITE);
        }catch (SQLException e){}
        //  if(db !=null){db.close();}
        return db;
    }

    public SQLiteDatabase getDB(){
        SQLiteDatabase db = null;
        try{
            db= SQLiteDatabase.openDatabase(getDbPath(),null,SQLiteDatabase.OPEN_READONLY);
        }catch (SQLException e){}
      //  if(db !=null){db.close();}
        return db;
    }


    public void copyDB(){


        if (checkDB() == false){
            try {
                Common.prn("start copying database file");
                InputStream is =context.getAssets().open(Common.DB_FILE);

               // new File(getDbPath()).delete();
                FileOutputStream os = new FileOutputStream(new File(getDbPath())) ;
                int i=0;
                int x =0;
                byte[] buff = new byte[1024];
                while ((i=is.read(buff))>0){
                    os.write(buff,0,i);
                }
                os.flush();
                os.close();
                is.close();

                Common.prn("successfully copied DB");
            }catch (IOException e){Common.prn("fail to copy db");Common.prn(e.toString());}
        }


    }



    @Override
    public void onCreate(SQLiteDatabase db) {}

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
}
