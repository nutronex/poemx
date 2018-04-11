package com.nutronex.antx.jasmine.views;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nutronex.antx.jasmine.R;
import com.nutronex.antx.jasmine.data.Post;
import com.nutronex.antx.jasmine.util.Common;

/**
 * Created by ak on 11/11/17.
 */

public class PostFragment extends Fragment {

    TextView title ;
    TextView author_name;
    TextView favourite ;
    TextView body;


    public PostFragment(){}

    public static PostFragment newInstance(Post d){
        PostFragment pf = new PostFragment();
        Bundle b = new Bundle();
        b.putString("title",d.title);
        b.putString("body",d.body);
        b.putBoolean("favourite",d.favourite);
        b.putString("author_name",d.authorName);
        pf.setArguments(b);
        return pf;
    }

    @Override
    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle b){
        View v = i.inflate(R.layout.post_layout,container,false);
        Bundle input = getArguments();
Typeface t = Typeface.createFromAsset(getActivity().getAssets(),"fonts/zawgyione.ttf");
   //     title =(TextView)v.findViewById(R.id.post_title);
        body=(TextView)v.findViewById(R.id.post_body);

        body.setText(input.getString("body"));
        body.setTypeface(t);
        RelativeLayout rl = (RelativeLayout)v.findViewById(R.id.post_container);

        rl.setBackground(Common.getRandomResource(getActivity()));

        return v;

    }
}
