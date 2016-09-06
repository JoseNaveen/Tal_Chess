package com.jose.tal_chess;

/**
 * Created by joalphon on 03-Sep-16.
 */

public class Piece {
    Type type = null;
    String color = null;
    String name = null;
    public Piece(Type type,String color,String name){
        this.type = type;
        this.color = color;
        this.name = name;
    }
    public void viewPiece(){
        System.out.println(this.name);
        System.out.println(this.color);
        this.type.viewType();

    }
}

