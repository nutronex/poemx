package com.nutronex.antx.jasmine;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.nutronex.antx.jasmine.data.PoemDao;
import com.nutronex.antx.jasmine.data.Post;
import com.nutronex.antx.jasmine.util.Common;
import com.nutronex.antx.jasmine.views.ActivityWithNavigationItemListener;
import com.nutronex.antx.jasmine.views.FavouriteListRViewAdaptor;
import com.nutronex.antx.jasmine.views.HomeViewAdaptor;

import java.util.ArrayList;

public class HomeActivity extends ActivityWithNavigationItemListener
                    implements  HomeViewAdaptor.HomeSelectListener{

    PoemDao data;
    HomeViewAdaptor ad;
    RecyclerView rv;
    ActionBarDrawerToggle toggle;

    void extractData(){
        data = new PoemDao(this);
        ad = new HomeViewAdaptor(this,data.getPoemTitle());
        RecyclerView.LayoutManager l = new LinearLayoutManager(this);
        rv.setLayoutManager(l);
        DividerItemDecoration deco = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        rv.addItemDecoration(deco);
        rv.setAdapter(ad);
    }




    @Override
    public void onClick(int pos) {
        Intent intent = new Intent(this, SinglePoemActivity.class);
        intent.putExtra(Common.VIEW_BY_ID,pos);
        startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
       // Common.prn("this is shit");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (Build.VERSION.SDK_INT >=21){
            getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.colorPrimary));
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        //ImageView iv = (ImageView) navigationView.findViewById(R.id.nav_header_img);


        int rnd =(int)(Math.random()*3)+1;
        int nav_header_photo_id =getResources().getIdentifier("ppph"+rnd,"drawable",getPackageName());
        navigationView.getHeaderView(0).setBackgroundResource(nav_header_photo_id);

        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);



        rv = (RecyclerView)findViewById(R.id.home_recycler_view);
        extractData();

        toggle.syncState();
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            //finish();
            //System.exit(0);
            AlertDialog diaglog = new AlertDialog.Builder(this)
                    .setTitle("Are you sure to Exit?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                             android.os.Process.killProcess(android.os.Process.myPid());
                             finish();
                             System.exit(0);
                        }
                    })
                    .setNegativeButton("No", null)
                    .create();
            diaglog.show();
           // super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        if(id== android.R.id.home){
    Common.prn("home is clicked");
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.openDrawer(GravityCompat.START);
            return true;
        }
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_exit) {
            AlertDialog diaglog = new AlertDialog.Builder(this)
                    .setTitle("Are you sure to Exit?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            android.os.Process.killProcess(android.os.Process.myPid());
                            finish();
                            System.exit(0);
                        }
                    })
                    .setNegativeButton("No", null)
                    .create();
            diaglog.show();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }





}
