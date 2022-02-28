package com.knightstour.domain;

public class Movement {

    private final int rowModifier;
    private final int columnModifier;

    public Movement(int rowModifier, int columnModifier) {
        this.rowModifier = rowModifier;
        this.columnModifier = columnModifier;
    }

    public Tile move(Tile tile) {
        return new Tile(tile.getRow() + rowModifier, tile.getColumn() + columnModifier);
    }
}
