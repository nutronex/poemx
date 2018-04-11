package com.nutronex.antx.jasmine;


import android.content.Intent;
import android.os.Build;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.nutronex.antx.jasmine.data.PoemDao;
import com.nutronex.antx.jasmine.util.Common;
import com.nutronex.antx.jasmine.views.ActivityWithNavigationItemListener;
import com.nutronex.antx.jasmine.views.AuthorListRViewAdaptor;
import com.nutronex.antx.jasmine.views.PoemSlidePagerAdaptor;

public class AuthorListActivity extends ActivityWithNavigationItemListener implements
        AuthorListRViewAdaptor.AuthorSelectListener
        {






    public void onClick(int pos){
      //  Common.prn(String.valueOf(pos));
        Intent intent = new Intent(this, PoemOfAuthorActivity.class);
        intent.putExtra(Common.POEM_BY_AUTHOR_ID,pos);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_author_list);

        RecyclerView rv = (RecyclerView)findViewById(R.id.author_list_rview);
        PoemDao p = new PoemDao(this);
        AuthorListRViewAdaptor ad = new AuthorListRViewAdaptor(this,p.getAuthorList());
        RecyclerView.LayoutManager l = new LinearLayoutManager(this);
        rv.setLayoutManager(l);
        DividerItemDecoration deco = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);

        rv.addItemDecoration(deco);
        if (Build.VERSION.SDK_INT >=21){
            getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.colorPrimary));
        }
        rv.setAdapter(ad);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Author list");
        //toolbar.setTitleTextColor(0);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
      //  getSupportActionBar().setHomeas
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//Common.prn("shit in author list");
      /**
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
       // drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
       */
    }


    @Override
    public void onBackPressed() {
            super.onBackPressed();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //    getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }







}

