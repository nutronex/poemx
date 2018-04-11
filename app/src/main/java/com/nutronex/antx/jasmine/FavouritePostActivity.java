package com.nutronex.antx.jasmine;

import android.content.Intent;
import android.os.Build;
import android.support.design.widget.NavigationView;
import android.support.v4.app.NavUtils;
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
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;

import com.nutronex.antx.jasmine.data.PoemDao;
import com.nutronex.antx.jasmine.data.Post;
import com.nutronex.antx.jasmine.util.Common;
import com.nutronex.antx.jasmine.views.ActivityWithNavigationItemListener;
import com.nutronex.antx.jasmine.views.AuthorListRViewAdaptor;
import com.nutronex.antx.jasmine.views.FavouriteListRViewAdaptor;

import static com.nutronex.antx.jasmine.R.id.toolbar;

public class FavouritePostActivity extends ActivityWithNavigationItemListener implements
        FavouriteListRViewAdaptor.FavouriteSelectListener{

    RecyclerView rv;
    PoemDao data;
    FavouriteListRViewAdaptor ad;

    @Override
    protected void onRestart() {
        super.onResume();
        extractData();
    }

    void extractData(){
        data = new PoemDao(this);
        ad = new FavouriteListRViewAdaptor(this,data.getFavourite());
        RecyclerView.LayoutManager l = new LinearLayoutManager(this);

        rv.setLayoutManager(l);
        DividerItemDecoration deco = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        rv.addItemDecoration(deco);
        rv.setAdapter(ad);

        ItemTouchHelper.SimpleCallback simple_item_touch = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int i = viewHolder.getAdapterPosition();
                Post p = ad.getItem(i);
                p.favourite=p.favourite?false:true;
                if(data.toggleFavourite(p)>-1){
                ad.removeFavourite(i);}
                //data.toggleFavourite(viewHolder.getAdapterPosition());
            }
        };
        ItemTouchHelper item_touch_helper = new ItemTouchHelper(simple_item_touch);
       item_touch_helper.attachToRecyclerView(rv);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_list);

        rv = (RecyclerView)findViewById(R.id.fav_list_rview);
        extractData();

        if (Build.VERSION.SDK_INT >=21){
            getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.colorPrimary));
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Favourite poems");

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);







       }

    @Override
    public void onClick(int pos) {
        Intent intent = new Intent(this, PoemSlideActivity.class);
        intent.putExtra(Common.VIEW_FAVOURITE,pos);
        startActivity(intent);
    }





    @Override
    public void onBackPressed() {

        finish();
            super.onBackPressed();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //  getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

       // Common.prn("some shits in favA");
        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }


}
