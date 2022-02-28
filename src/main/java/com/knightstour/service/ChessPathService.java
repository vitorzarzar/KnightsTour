package com.knightstour.service;

import com.knightstour.domain.Movement;
import com.knightstour.domain.Tile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class ChessPathService {

    private static final int BOARD_SIZE = 10;
    private static final int UNVISITED_TILE = -1;

    private static final List<Movement> MOVEMENT_LIST = List.of(
        new Movement(+1, -2),
        new Movement(-1, -2),
        new Movement(-2, -1),
        new Movement(-2, +1),
        new Movement(-1, +2),
        new Movement(+1, +2),
        new Movement(+2, +1),
        new Movement(+2, -1)
    );

    private static List<Integer> BOARD;

    public static int boardIndex(Tile tile) {
        return (tile.getColumn() * BOARD_SIZE) + tile.getRow();
    }

    public static List<Integer> findPath(String startTile) {
        int column = startTile.toUpperCase().charAt(0) - 65;
        int row = Integer.parseInt(startTile.substring(1));

        boolean foundResult;
        do {
            foundResult = findResult(new Tile(row, column));
        } while(!foundResult);

        return BOARD;
    }

    private static boolean findResult(Tile firstTile) {
        BOARD = new ArrayList<>(Collections.nCopies(BOARD_SIZE * BOARD_SIZE, UNVISITED_TILE));

        int order = UNVISITED_TILE + 1;
        BOARD.set(boardIndex(firstTile), order);
        order++;

        Tile currentTile = firstTile;
        for(int i = 0; i < BOARD_SIZE * BOARD_SIZE - 1; i++, order++) {
            Tile nextTile = nextTile(currentTile, order);

            if(nextTile == null) return false;

            currentTile = nextTile;
        }

        return BOARD.stream().noneMatch(i -> i == UNVISITED_TILE);
    }

    private static Tile nextTile(Tile currentTile, int orderNumber) {
        long minPossibleMoves = BOARD_SIZE + 1;
        Tile nextTile = null;

        int randomizer = ThreadLocalRandom.current().nextInt(BOARD_SIZE);
        for(int i = 0; i < MOVEMENT_LIST.size(); i++) {
            int randomIndex = (i + randomizer) % MOVEMENT_LIST.size();
            Movement movement = MOVEMENT_LIST.get(randomIndex);

            Tile movedTile = movement.move(currentTile);
            long possibleMoves = possibleMoves(movedTile);

            if(validNextTile(movedTile) && possibleMoves < minPossibleMoves) {
                minPossibleMoves = possibleMoves;
                nextTile = movedTile;
            }
        }

        if(nextTile != null) BOARD.set(boardIndex(nextTile), orderNumber);

        return nextTile;
    }

    private static long possibleMoves(Tile tile) {
        return MOVEMENT_LIST.stream()
            .filter(m -> validNextTile(m.move(tile)))
            .count();
    }

    private static boolean validNextTile(Tile tile) {
        return tile.getColumn() >= 0 && tile.getColumn() < BOARD_SIZE
            && tile.getRow() >= 0 && tile.getRow() < BOARD_SIZE
            && BOARD.get(boardIndex(tile)).equals(UNVISITED_TILE);
    }
}
