package com.nutronex.antx.jasmine.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.nutronex.antx.jasmine.util.Common;

import java.util.ArrayList;
import java.util.HashMap;
import android.content.ContentValues;

/**
 * Created by ak on 11/10/17.
 */

public class PoemDao {


    static Context c;
    static DbHelper db;
    public PoemDao(Context c){
        this.c =c;
        if(db==null){
            db = new DbHelper(c);
        }
        db.copyDB();
        //if(!db.checkDB()){db.copyDB();}
    }

    SQLiteDatabase getDBWritable(){
        return db.getDBWritable();
    }

    SQLiteDatabase getDB(){
        return db.getDB();
    }

    void closeDb(){if (db!=null){db.close();}}


    public ArrayList<Author> getAuthorList(){
       // ArrayList<String> l =new ArrayList<>();
        ArrayList<Author> hm = new ArrayList<>();
        Cursor c = getDB().rawQuery("select * from one_author ;",null);
        c.moveToFirst();
        do{
            hm.add(  new Author(c.getInt(c.getColumnIndex("id")),
                                c.getString(c.getColumnIndex("name")),
                                c.getInt(c.getColumnIndex("count"))));

           // Common.prn(String.valueOf(c.getInt(c.getColumnIndex("count"))));
        }while(c.moveToNext());
        closeDb();
        return hm;
    }




    public ArrayList<Post> getAllPoems(){

        ArrayList <Post> plist = new ArrayList<>();
        String getPoemSQL = "select title,one_author.name as author, "+
                "body,one_post.id as postId,one_author.id as authorId,favourite "+
                "from one_post join one_author where one_author.id=one_post.author_id order by random() ";

        //ArrayList<Post> hm=new ArrayList<>();

        String query = getPoemSQL;
        Cursor c = getDB().rawQuery(query,null);
        c.moveToFirst();

        do{
        Post post = new Post(c.getString(c.getColumnIndex("title")),
                c.getString(c.getColumnIndex("body")),
                c.getInt(c.getColumnIndex("postId")),
                c.getInt(c.getColumnIndex("authorId")),
                c.getString(c.getColumnIndex("author")),
                c.getInt(c.getColumnIndex("favourite")));
            plist.add(post);
        }while(c.moveToNext());
        closeDb();
        return plist;

    }



    public ArrayList<Post> getAllPoems(int author_id){

        ArrayList <Post> plist = new ArrayList<>();
        String getPoemSQL = "select title,one_author.name as author, "+
                "body,one_post.id as postId,one_author.id as authorId,favourite "+
                "from one_post join one_author where one_author.id=one_post.author_id and one_author.id="+author_id;

        //ArrayList<Post> hm=new ArrayList<>();

        String query = getPoemSQL;
        Cursor c = getDB().rawQuery(query,null);
        c.moveToFirst();

        do{
            Post post = new Post(c.getString(c.getColumnIndex("title")),
                    c.getString(c.getColumnIndex("body")),
                    c.getInt(c.getColumnIndex("postId")),
                    c.getInt(c.getColumnIndex("authorId")),
                    c.getString(c.getColumnIndex("author")),
                    c.getInt(c.getColumnIndex("favourite")));
            plist.add(post);
        }while(c.moveToNext());
        closeDb();
        return plist;

    }





    public Post getPoem(int id){

        String getPoemSQL = "select title,one_author.name as author, "+
                            "body,one_post.id as postId,one_author.id as authorId,favourite "+
                            "from one_post join one_author where one_author.id=one_post.author_id ";

        //ArrayList<Post> hm=new ArrayList<>();

        String query = getPoemSQL+"and one_post.id="+id;
        Cursor c = getDB().rawQuery(query,null);
        c.moveToFirst();

        Post post = new Post(c.getString(c.getColumnIndex("title")),
                                c.getString(c.getColumnIndex("body")),
                    c.getInt(c.getColumnIndex("postId")),
                    c.getInt(c.getColumnIndex("authorId")),
                c.getString(c.getColumnIndex("author")),
                    c.getInt(c.getColumnIndex("favourite")));



        //    hm.add(arr);
        //}while(c.moveToNext());
        closeDb();
        return post;

    }

    public ArrayList<Post > getPoemTitle(){
        ArrayList<Post> hm=new ArrayList<>();
        Cursor c = getDB().rawQuery("select one_post.id as idx, title ,"+
                " one_author.name as author , one_post.favourite as favourite" +
                " from one_post "+
                " join one_author where one_author.id=one_post.author_id "
                +" order by random();"
                ,null);
        c.moveToFirst();
        do{
            Post p = new Post(c.getString(c.getColumnIndex("title")),
                           c.getString(c.getColumnIndex("author")));
            p.id =c.getInt(c.getColumnIndex("idx"));
            p.favourite =(c.getInt(c.getColumnIndex("favourite"))==1)?true
                    :false;
            hm.add(p);
        }while(c.moveToNext());
        closeDb();
        return hm;
    }


    public int isFavourite(int id){
        Cursor c =getDB().rawQuery("select favourite from one_post where id="+String.valueOf(id),null);
        c.moveToFirst();
        int i = c.getInt(c.getColumnIndex("favourite"));
        closeDb();
        return i;
    }

    public ArrayList<Post> getFavourite(){
        ArrayList<Post> hm =new ArrayList<>();
        String getPoemSQL = "select title,one_author.name as author, "+
                "body,one_post.id as postId,one_author.id as authorId,favourite "+
                "from one_post join one_author where one_author.id=one_post.author_id and favourite = 1";

        Cursor c = getDB().rawQuery(getPoemSQL,null);
        c.moveToFirst();
        //Common.prn();
        if(c.getCount()>0){
            do{
                Post post = new Post(c.getString(c.getColumnIndex("title")),
                        c.getString(c.getColumnIndex("body")),
                        c.getInt(c.getColumnIndex("postId")),
                        c.getInt(c.getColumnIndex("authorId")),
                        c.getString(c.getColumnIndex("author")),
                        c.getInt(c.getColumnIndex("favourite")));

                hm.add(post);
            }while(c.moveToNext());
        }
        closeDb();
        return hm;
    }

    public int toggleFavourite(Post p){
        int fav = p.favourite?1:0;
        SQLiteDatabase db = getDBWritable();
        ContentValues c =new ContentValues();
        c.put("favourite",fav);
        String postId = String.valueOf(p.id);
        String[] arg = {postId};
        int id =db.update(Post.table_name,c," id =?",arg);
        Common.prn("db hit"+p.title+" "+String.valueOf(p.favourite));
        closeDb();
        return id;
    }


    public ArrayList<Post> getPoemTitleByAuthor(int id){
        ArrayList<Post> hm=new ArrayList<>();
        Cursor c = getDB().rawQuery("select one_post.id as idx, title ,"+
                        " one_post.favourite as favourite" +
                        " from one_post "+
                        " join one_author where one_author.id=one_post.author_id "+
                        "and one_author.id = "+String.valueOf(id)

                ,null);
        c.moveToFirst();
        do{
            Post p = new Post(c.getString(c.getColumnIndex("title")),
                    c.getString(c.getColumnIndex("author")));
            p.id =c.getInt(c.getColumnIndex("idx"));
            p.favourite =(c.getInt(c.getColumnIndex("favourite"))==1)?true
                    :false;
            hm.add(p);
        }while(c.moveToNext());
        closeDb();
        return hm;
    }
}
