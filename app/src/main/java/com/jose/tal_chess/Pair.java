package com.jose.tal_chess;

/**
 * Created by joalphon on 03-Sep-16.
 */
public class Pair {
    Cell cell = null;
    Piece piece = null;
    public Pair(Cell cell,Piece piece){
        this.cell = cell;
        this.piece = piece;
    }
    public void viewPair(){
        this.cell.viewCell();
        this.piece.viewPiece();

    }
}