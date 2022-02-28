package com.knightstour.domain;

public class Tile {

    private final int row;
    private final int column;

    public Tile(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

}