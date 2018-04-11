package com.nutronex.antx.jasmine.views;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nutronex.antx.jasmine.AuthorListActivity;
import com.nutronex.antx.jasmine.R;
import com.nutronex.antx.jasmine.data.Author;

import java.util.ArrayList;

/**
 * Created by ak on 11/11/17.
 */

public class AuthorListRViewAdaptor extends RecyclerView.Adapter<AuthorListRViewAdaptor.Vholder> {


    Context c ;
    AuthorListActivity activity;
    Typeface zawgyifont ;
    ArrayList<Author> data ;

    public interface AuthorSelectListener{
        public void onClick(int pos);
    }

    public void handleClick(int i){
        activity.onClick(i);
    }

    @Override
    public Vholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.author_list_item,parent,false);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence s =((TextView)  v.findViewById(R.id.authorlist_authorid)).getText();
                handleClick(Integer.parseInt((String)s));
            }
        });
        return new Vholder(v);
    }

    @Override
    public void onBindViewHolder(Vholder holder, int position) {
        holder.name.setText(data.get(position).name+ " ( "+data.get(position).count +" )");
        holder.id.setText(String.valueOf(data.get(position).id));
     //   holder.count.setText(String.valueOf(data.get(position).count));

    }

    @Override
    public int getItemCount() {
        return this.data.size();
    }

    public class Vholder extends RecyclerView.ViewHolder{
        public TextView name ;
        public TextView id ;
        //public TextView count;
        public Vholder(View v){
            super(v);
            this.id=(TextView)v.findViewById(R.id.authorlist_authorid);
            this.name =(TextView) v.findViewById(R.id.author_name);
         //   this.count = (TextView)v.findViewById(R.id.poem_count);
            this.name.setTypeface(zawgyifont);
        }
    }

    public AuthorListRViewAdaptor(Context c,ArrayList<Author> authorlist) {
        this.data = authorlist;
        this.activity = (AuthorListActivity)c;

     //   this.context = c;
        zawgyifont = Typeface.createFromAsset(c.getAssets(),"fonts/zawgyione.ttf");
    }

}
