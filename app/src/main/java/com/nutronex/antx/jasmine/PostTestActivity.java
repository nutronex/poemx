package com.nutronex.antx.jasmine;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nutronex.antx.jasmine.util.Common;


public class PostTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_layout);



        RelativeLayout rl = (RelativeLayout)findViewById(R.id.post_container);
       // Drawable d = rl.getBackground();
                ///int myid = getResources().getIdentifier("p2","drawable",getPackageName());
                //Bitmap bf = BitmapFactory.decodeResource(getResources(),myid);
                //BitmapDrawable bd = new BitmapDrawable(getResources(),bf);
                //bd.setAlpha(112);
        rl.setBackground(Common.getRandomResource(this));

  //      BitmapDrawable bd =(BitmapDrawable) rl.getBackground();
//        Bitmap bf = BitmapFactory.decodeResource(getResources(),R.drawable.p1);

        //d.setAlpha((int) 0.5);
        //TextView title = (TextView )findViewById(R.id.post_title);
       // TextView body = (TextView)findViewById(R.id.)
    }
}
