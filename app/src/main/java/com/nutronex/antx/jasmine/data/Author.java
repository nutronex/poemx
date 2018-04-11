package com.nutronex.antx.jasmine.data;

/**
 * Created by ak on 11/10/17.
 */

public class Author {
    public String name ;
    public int id ;
    public int count;
    public Author( int id,String n,int numOfPoem){
        this.name = n;
        this.id = id;
        count = numOfPoem;
    }


}
