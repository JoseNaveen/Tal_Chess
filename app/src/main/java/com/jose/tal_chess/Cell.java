package com.jose.tal_chess;

/**
 * Created by joalphon on 03-Sep-16.
 */
public class Cell {
    String clr = null;
    int pos = 0;
    String notation = null;
    public Cell(String clr,int pos,String notation){
        this.clr = clr;
        this.pos = pos;
        this.notation = notation;
    }
    public void viewCell(){
        System.out.println(this.clr);
        System.out.println(this.pos);
        System.out.println(this.notation);
    }
}