package com.nutronex.antx.jasmine.data;

/**
 * Created by ak on 11/10/17.
 */

public class Post {

    public static String table_name ="one_post";
    public int id ;
    public String title ;
    public String body ;
    public int authorId ;
    public String authorName;
    public boolean favourite;

    public Post(String t,String b, int id, int authorId,String authorName,int favourite){
        this.title = t;
        this.body = b;
        this.id = id;
        this.authorName = authorName;
        this.authorId = authorId;
        this.favourite = (favourite==1 ?true:false);
    }

    public Post(){}

    public Post(String title,String author){
        this.title = title;
        this.authorName = author;
    }
}
