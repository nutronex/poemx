package com.nutronex.antx.jasmine;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.icu.text.DisplayContext;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.nutronex.antx.jasmine.data.PoemDao;
import com.nutronex.antx.jasmine.data.Post;
import com.nutronex.antx.jasmine.util.Common;
import com.nutronex.antx.jasmine.views.PoemSlidePagerAdaptor;

public class PoemSlideActivity extends AppCompatActivity {

    FloatingActionButton fab;
    BottomSheetBehavior bh;
    TextView bsheet_title;
    TextView bsheet_author;
    ViewPager vp;
    CheckBox bsheet_favourite;
    PoemSlidePagerAdaptor ad;
    PoemDao data;

    public boolean onOptionsItemSelected(MenuItem item) {
       Common.prn(String.valueOf(item.getItemId()));
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poem_slide);

        if (Build.VERSION.SDK_INT >=21){
            getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.colorPrimary));
        }


        data = new PoemDao(this);
        bsheet_favourite = (CheckBox)findViewById(R.id.bsheet_fav);
        bsheet_title=(TextView)findViewById(R.id.bsheet_title);
        bsheet_author=(TextView)findViewById(R.id.bsheet_author);
        Typeface zawgyi = Typeface.createFromAsset(getAssets(),"fonts/zawgyione.ttf");
        bsheet_title.setTypeface(zawgyi);
        bsheet_author.setTypeface(zawgyi);

        final Bundle b=getIntent().getExtras();

        vp = (ViewPager)findViewById(R.id.post_viewpager);
        NestedScrollView nv =(NestedScrollView)findViewById(R.id.bottomsheet);
        fab =(FloatingActionButton)findViewById(R.id.fab);
        bh =BottomSheetBehavior.from(nv);


        bsheet_favourite.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView.isPressed()) {
                    Post p = ad.getInfo(vp.getCurrentItem());
                    p.favourite=isChecked?true:false;
                //    Common.prn("favourtie is checked " + String.valueOf(isChecked) + " " + p.title);
                    int i = new PoemDao(PoemSlideActivity.this).toggleFavourite(p);

                }

            }
        });

        //set initial state(hide) of bottomsheet
        bh.setHideable(true);
        bh.setState(BottomSheetBehavior.STATE_HIDDEN);

        Toolbar tb =(Toolbar)findViewById(R.id.slidetoolbar);

        tb.setTitle("");
        tb.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back_black_24dp));
        tb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                //
                //
                // finish();
            }
        });
        setSupportActionBar(tb);
       // tb.setTitleTextColor(Color.RED);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //--1----check how to start activity whether for VIewall for Poem or by author

        ad = new PoemSlidePagerAdaptor(getSupportFragmentManager());
        if(b!=null){
            Common.prn(String.valueOf(b.getInt(Common.VIEW_BY_AUTHOR_ID)));
            if (b.getInt(Common.VIEW_BY_AUTHOR_ID)>0){
                int id=b.getInt(Common.VIEW_BY_AUTHOR_ID);
                ad.setData(data.getAllPoems(id));
            }else{
                ad.setData(data.getFavourite());
            }

        }else{
            ad.setData(data.getAllPoems());
        }
        //---1-end--

        vp.setAdapter(ad);

       // vp.setOffscreenPageLimit(10);
        if(b!=null && b.getInt(Common.VIEW_FAVOURITE)>-1) {
            vp.setCurrentItem(b.getInt(Common.VIEW_FAVOURITE));

          //  bsheet_favourite.setVisibility(View.INVISIBLE);
        }


        //---2---viewpager listener to hide bottomsheet when vp is swiped
        vp.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                bh.setState(BottomSheetBehavior.STATE_HIDDEN);
                return false;
            }
        });
        //---2--end---




        //---3--set attribute of bottomsheet's title according to current Item and hide Fab button
        bh.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if(newState ==BottomSheetBehavior.STATE_HIDDEN){
                    fab.setVisibility(View.VISIBLE);
                }
                if(newState == BottomSheetBehavior.STATE_EXPANDED){
                Post p = ad.getInfo(vp.getCurrentItem());
                bsheet_title.setText(p.title);
                bsheet_author.setText(p.authorName);
                bsheet_favourite.setChecked(data.isFavourite(p.id)==1?true:false);
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset){}
        });
        //---3--end---


        //---4---make bottomsheet visible and hide Fab when Fab is clicked
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fab.setVisibility(View.INVISIBLE);
                bh.setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        });
        //---4---end---





    }
    @Override
    public void onBackPressed() {

        finish();
        //super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
}




