package com.nutronex.antx.jasmine;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.nutronex.antx.jasmine.data.PoemDao;
import com.nutronex.antx.jasmine.data.Post;
import com.nutronex.antx.jasmine.util.Common;
import com.nutronex.antx.jasmine.views.HomeViewAdaptor;
import com.nutronex.antx.jasmine.views.PoemOfAuthorAdaptor;

import java.util.ArrayList;

public class PoemOfAuthorActivity extends AppCompatActivity
        implements  HomeViewAdaptor.HomeSelectListener{


    PoemDao data;
    PoemOfAuthorAdaptor ad;
    RecyclerView rv;
    int author_id;





    @Override
    public void onClick(int pos) {
        Intent intent = new Intent(this, SinglePoemActivity.class);
        intent.putExtra(Common.VIEW_BY_ID,pos);
        startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poem_of_author);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);



        if (Build.VERSION.SDK_INT >=21){
            getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.colorPrimary));
        }

        rv = (RecyclerView)findViewById(R.id.poem_by_author_recycler_view);

        data = new PoemDao(this);
        RecyclerView.LayoutManager l = new LinearLayoutManager(this);
        rv.setLayoutManager(l);
        DividerItemDecoration deco = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        rv.addItemDecoration(deco);


        final Bundle b=getIntent().getExtras();
        if(b!=null){
            if (b.getInt(Common.POEM_BY_AUTHOR_ID)>0){
                int id=b.getInt(Common.POEM_BY_AUTHOR_ID);
                ArrayList<Post> posts = data.getAllPoems(id);
                ad = new PoemOfAuthorAdaptor(this,posts);
                toolbar.setTitle(posts.get(0).authorName);
                Common.prn(posts.get(0).authorName);
                rv.setAdapter(ad);
            }
        }




        Typeface zawgyione = Typeface.createFromAsset(getAssets(),"fonts/zawgyione.ttf");
        int numOfChild = toolbar.getChildCount();
        for (int i=0;i<numOfChild;i++){
            View v = toolbar.getChildAt(i);
            if(v instanceof TextView){
                ((TextView) v).setTypeface(zawgyione);
            }
        }

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }
}
