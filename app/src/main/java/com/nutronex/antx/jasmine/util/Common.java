package com.nutronex.antx.jasmine.util;

import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.graphics.drawable.BitmapDrawable;

import com.nutronex.antx.jasmine.R;

import java.util.Random;

/**
 * Created by ak on 11/10/17.
 */

public class Common {

    public static String VIEW_BY_AUTHOR_ID="VIEWBYAUTHORID";
    public static String VIEW_FAVOURITE="VIEWFAVOURITE";
    public static String VIEW_BY_ID="VIEW_BY_ID";
    public static String DB_FILE ="pppoem.sqlite3";
    public static String POEM_BY_AUTHOR_ID = "POEMBYAUTHOR";



     public static String[] COLOR=
             {"#FF4081",
            "#40e6ff",
            "#70ff40",
            "#50ff40",
            "#5940ff",
            "#d2ea4c",
            "#f24437",
            "#fd1866",
            "#32ef15",
            "#ce2ef5",
            "#f4167a",
            "#FF4081",
            "#40ff83",
            "#ff9340",
            "#ca02c0",
            "#40ff43"};

    public static void prn(String s){
        Log.d("-----x----",s);
    }

    public static BitmapDrawable getRandomResource(Context c){
        int rd =(int)(Math.random()*36)+1;
        prn(String.valueOf(rd));
        int id =c.getResources().getIdentifier("p"+rd,"drawable",c.getPackageName());
        prn(String.valueOf(id));
        Bitmap bitmap = BitmapFactory.decodeResource(c.getResources(),id);
        BitmapDrawable b = new BitmapDrawable(c.getResources(),bitmap);
        b.setAlpha(52);
        return  b;

    }

}
