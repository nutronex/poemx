package com.nutronex.antx.jasmine.views;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.Shape;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nutronex.antx.jasmine.HomeActivity;
import com.nutronex.antx.jasmine.R;
import com.nutronex.antx.jasmine.data.Post;
import com.nutronex.antx.jasmine.util.Common;

import java.util.ArrayList;

/**
 * Created by ak on 11/11/17.
 */

public class HomeViewAdaptor extends RecyclerView.Adapter<HomeViewAdaptor.Vholder> {


    Context c ;
    HomeActivity activity;
    Typeface zawgyifont ;
    ArrayList<Post> data ;


    public HomeViewAdaptor(Context c, ArrayList<Post> poemlist) {
        this.data = poemlist;
        this.activity = (HomeActivity)c;
        this.c =c ;
        zawgyifont = Typeface.createFromAsset(c.getAssets(),"fonts/zawgyione.ttf");
    }

    public interface HomeSelectListener{
        public void onClick(int pos);
    }



    public void handleClick(int i){
        activity.onClick(i);
    }


    @Override
    public Vholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_rv_item,parent,false);
        //ShapeDrawable sd = (ShapeDrawable) v.getBackground();

       // ((RelativeLayout)v.findViewById(R.id.home_rv_container)).setBackgroundColor(id);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence s =((TextView)  v.findViewById(R.id.home_rview_id)).getText();
                handleClick(Integer.parseInt((String)s));
            }
        });
        return new Vholder(v);
    }



    @Override
    public void onBindViewHolder(Vholder holder, int position) {

      //  Drawable sd = Drawable.createFromPath("R.drawable.home_item_bg");

      //  Shape s =ShapeDrawable
      //  ShapeDrawable se = new ShapeDrawable(sd);
//        holder.title.setTextColor();
        holder.author_name.setText(data.get(position).authorName);
        holder.title.setText(data.get(position).title);
     //   Common.prn("shittttt"+String.valueOf(data.get(position).id));
        holder.id.setText(String.valueOf(data.get(position).id));
        if(data.get(position).favourite){
            holder.fav.setVisibility(View.VISIBLE);
            holder.fav.setImageResource(R.drawable.ic_fav_indicator);

        }else{
            holder.fav.setVisibility(View.INVISIBLE);
        }


    }

    @Override
    public int getItemCount() {
        return this.data.size();
    }

    public class Vholder extends RecyclerView.ViewHolder{
        public TextView title;
        public View container;
        public TextView author_name ;
        public TextView id ;
        public  ImageView fav;
        public Vholder(View v){
            super(v);
            this.container = v;
            this.fav =(ImageView)v.findViewById(R.id.poemlist_fav_indicator);
            this.id=(TextView)v.findViewById(R.id.home_rview_id);
            this.title = (TextView)v.findViewById(R.id.home_rview_title);
            this.author_name =(TextView) v.findViewById(R.id.home_rview_author);
            this.author_name.setTypeface(zawgyifont);
            this.title.setTypeface(zawgyifont);
        }
    }



}
