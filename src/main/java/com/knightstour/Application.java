package com.knightstour;

import com.knightstour.domain.Tile;
import com.knightstour.service.ChessPathService;

import java.util.List;

import static com.knightstour.service.ChessPathService.boardIndex;

public class Application {

    public static void main(String[] args) {
        String tile = args[0];
        if(tile == null) return;

        printBoard(ChessPathService.findPath(tile));
    }

    private static void printBoard(List<Integer> list) {
        System.out.println("   A  B  C  D  E  F  G  H  I  J");
        for (int i = 0; i < 10; ++i) {
            System.out.printf("%d| ", i);

            for (int j = 0; j < 10; ++j)
                System.out.printf("%02d ", list.get(boardIndex(new Tile(i, j))));

            System.out.println();
        }
    }
}