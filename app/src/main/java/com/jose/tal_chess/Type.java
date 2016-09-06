package com.jose.tal_chess;

/**
 * Created by joalphon on 03-Sep-16.
 */

public class Type {
    int value = 0;
    String name = null;
    public Type(String name,int value){
        this.value = value;
        this.name = name;
    }
    public void viewType(){
        System.out.println(this.value);
        System.out.println(this.name);

    }

}

