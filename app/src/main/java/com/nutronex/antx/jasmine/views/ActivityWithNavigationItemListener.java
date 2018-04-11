package com.nutronex.antx.jasmine.views;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.nutronex.antx.jasmine.AboutActivity;
import com.nutronex.antx.jasmine.AuthorListActivity;
import com.nutronex.antx.jasmine.CreditActivity;
import com.nutronex.antx.jasmine.FavouritePostActivity;
import com.nutronex.antx.jasmine.HomeActivity;
import com.nutronex.antx.jasmine.PoemSlideActivity;
import com.nutronex.antx.jasmine.R;

/**
 * Created by ak on 12/1/17.
 */
 public  class ActivityWithNavigationItemListener extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

        @SuppressWarnings("StatementWithEmptyBody")
        @Override
        public boolean onNavigationItemSelected(MenuItem item) {
            // Handle navigation view item clicks here.
            int id = item.getItemId();

            if (id == R.id.nav_camera) {
              onBackPressed();
                //  Intent intent = new Intent(this, HomeActivity.class);
               // startActivity(intent);
                //finish();
                // Handle the camera action
            } else if (id == R.id.nav_favlist) {
                //Common.prn("nav fav is clicked in author");
                Intent intent = new Intent(this, FavouritePostActivity.class);
                startActivity(intent);
             //   finish();
            } else if (id == R.id.nav_author) {
                //  Common.prn("nav author is clicked in author");
                Intent intent = new Intent(this, AuthorListActivity.class);
                startActivity(intent);
                //finish();
            } else if (id == R.id.nav_slide) {
                Intent intent = new Intent(this, PoemSlideActivity.class);
                startActivity(intent);
                //finish();
            } else if (id == R.id.nav_ack) {
                Intent intent = new Intent(this, CreditActivity.class);
                startActivity(intent);
                //finish();
            } else if (id == R.id.nav_about) {
                Intent intent = new Intent(this, AboutActivity.class);
                startActivity(intent);
                //finish();
            }

                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
               drawer.closeDrawer(GravityCompat.START);
            return true;
        }

}
