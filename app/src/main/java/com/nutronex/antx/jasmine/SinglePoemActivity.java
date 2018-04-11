package com.nutronex.antx.jasmine;

import android.graphics.Typeface;
import android.os.Build;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nutronex.antx.jasmine.data.PoemDao;
import com.nutronex.antx.jasmine.data.Post;
import com.nutronex.antx.jasmine.util.Common;

import org.w3c.dom.Text;

public class SinglePoemActivity extends AppCompatActivity {

    PoemDao data;Post p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_poem);

        Bundle b = getIntent().getExtras();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if(b!=null){
            int id = b.getInt(Common.VIEW_BY_ID);
             data = new PoemDao(this);
             p = data.getPoem(id);
            TextView body = ((TextView)findViewById(R.id.post_body));

            body.setText(p.body);

            toolbar.setTitle(p.title);
            toolbar.setSubtitle(p.authorName);
            CoordinatorLayout rl = (CoordinatorLayout) findViewById(R.id.post_container);
            if (Build.VERSION.SDK_INT >=21){
                getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.colorPrimary));
            }
            Typeface zawgyione = Typeface.createFromAsset(getAssets(),"fonts/zawgyione.ttf");
           int numOfChild = toolbar.getChildCount();
            for (int i=0;i<numOfChild;i++){
                View v = toolbar.getChildAt(i);
                if(v instanceof  TextView){
                    ((TextView) v).setTypeface(zawgyione);
                }
            }

            rl.setBackground(Common.getRandomResource(this));
            body.setTypeface(zawgyione);
        }

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       // return super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.single_poem_menu,menu);
        MenuItem mi = menu.findItem(R.id.action_favourite);
        if(p!=null && p.favourite){
            Common.prn("selected poem is fav ---");
            mi.setIcon(R.drawable.menu_fav_yes);
        }else {
            Common.prn("selected poem is fav ---");

            mi.setIcon(R.drawable.menu_fav_no);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() ==R.id.action_favourite){
            p.favourite=p.favourite?false:true;
            data.toggleFavourite(p);

            invalidateOptionsMenu();
            //recreate();

        }
        return super.onOptionsItemSelected(item);
    }
}
