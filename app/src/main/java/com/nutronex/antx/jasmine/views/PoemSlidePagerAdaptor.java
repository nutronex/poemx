package com.nutronex.antx.jasmine.views;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.nutronex.antx.jasmine.data.Post;
import com.nutronex.antx.jasmine.util.Common;

import java.util.ArrayList;

/**
 * Created by ak on 11/11/17.
 */

public class PoemSlidePagerAdaptor extends FragmentStatePagerAdapter {

    ArrayList<Post> data ;
    public PoemSlidePagerAdaptor(FragmentManager fm) {
        super(fm);
    }


    public void setData(ArrayList<Post> data){
        this.data =data;
    }

    public void toggleFavorite(Post p){
        //this.data.get(id).favourite = data.get(id).favourite ? false : true;
        data.get(data.indexOf(p)).favourite=p.favourite;
        notifyDataSetChanged();
    }

    public void setFavourite(Post p){
    Common.prn("set fav xxx");
    //this.data.get(id).favourite = data.get(id).favourite ? false : true;

        this.data.remove(p);
        notifyDataSetChanged();
    }

    public void unsetFavourite(Post p){
        Common.prn("unset fav");
        this.data.remove(p);
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        return PostFragment.newInstance(data.get(position));
       // return null;
    }

    public Post getInfo(int id){
        return this.data.get(id);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public CharSequence getPageTitle(int pos){
        return data.get(pos).title;
    }
}
