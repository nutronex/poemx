package com.nutronex.antx.jasmine;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.Date;
import com.nutronex.antx.jasmine.data.Author;
import com.nutronex.antx.jasmine.data.PoemDao;
import com.nutronex.antx.jasmine.data.Post;
import com.nutronex.antx.jasmine.util.Common;
import com.nutronex.antx.jasmine.data.DbHelper;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import android.content.Intent;
import android.util.Log;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    boolean db_ready=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                PoemDao poem =new PoemDao(MainActivity.this);
                Intent i = new Intent(MainActivity.this,HomeActivity.class);

                startActivity(i);
                MainActivity.this.finish();
           //     Thread.sleep(4000);
            }
        },2000);


        //Intent i = new Intent(this,AuthorListActivity.class);
     //    Intent i = new Intent(this,HomeActivity.class);
//        Intent i = new Intent(this,PoemSlideActivity.class);
        //  i.putExtra(Common.VIEW_BY_ID,5000);
        //
  //      startActivity(i);
        //MainActivity.this.finish();


    }

}
