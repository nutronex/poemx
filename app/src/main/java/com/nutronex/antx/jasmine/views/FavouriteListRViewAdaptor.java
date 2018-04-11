package com.nutronex.antx.jasmine.views;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nutronex.antx.jasmine.AuthorListActivity;
import com.nutronex.antx.jasmine.FavouritePostActivity;
import com.nutronex.antx.jasmine.R;
import com.nutronex.antx.jasmine.data.Author;
import com.nutronex.antx.jasmine.data.Post;

import java.util.ArrayList;

/**
 * Created by ak on 11/11/17.
 */

public class FavouriteListRViewAdaptor extends RecyclerView.Adapter<FavouriteListRViewAdaptor.Vholder> {


    Context c ;
    FavouritePostActivity activity;
    Typeface zawgyifont ;
    ArrayList<Post> data ;

    public interface FavouriteSelectListener{
        public void onClick(int pos);
    }

    public Post getItem(int i){return  data.get(i);}

    public void handleClick(int i){
        activity.onClick(i);
    }

    @Override
    public Vholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fav_list_item,parent,false);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence s =((TextView)  v.findViewById(R.id.fav_item_id)).getText();

                handleClick(Integer.parseInt((String)s));
            }
        });
        return new Vholder(v);
    }

    public void removeFavourite(int pos){
        data.remove(pos);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(Vholder holder, int position) {
        holder.author_name.setText(data.get(position).authorName);
        holder.title.setText(data.get(position).title);
        holder.id.setText(String.valueOf(position));

    }

    @Override
    public int getItemCount() {
        return this.data.size();
    }

    public class Vholder extends RecyclerView.ViewHolder{
        public TextView title;
        public TextView author_name ;
        public TextView id ;
        public Vholder(View v){
            super(v);
            this.id=(TextView)v.findViewById(R.id.fav_item_id);
            this.title = (TextView)v.findViewById(R.id.fav_item_title);
            this.author_name =(TextView) v.findViewById(R.id.fav_item_author);
            this.author_name.setTypeface(zawgyifont);
            this.title.setTypeface(zawgyifont);
        }
    }

    public FavouriteListRViewAdaptor(Context c, ArrayList<Post> authorlist) {
        this.data = authorlist;
        this.activity = (FavouritePostActivity)c;

     //   this.context = c;
        zawgyifont = Typeface.createFromAsset(c.getAssets(),"fonts/zawgyione.ttf");
    }

}
