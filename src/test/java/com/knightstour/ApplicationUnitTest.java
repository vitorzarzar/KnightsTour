package com.knightstour;

import com.knightstour.service.ChessPathService;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ApplicationUnitTest {

    public static String generateTile() {
        int columnNumber = ThreadLocalRandom.current().nextInt(65,75);
        int rowNumber = ThreadLocalRandom.current().nextInt(10);

         return Character.toString(columnNumber) + rowNumber;
    }

    @Test
    void validateEveryTileWhereVisited() {
        List<Integer> result = ChessPathService.findPath(generateTile());

        assertTrue(result.stream()
                .noneMatch(i -> i < 0));
    }

    @Test
    void validateEveryTileWhereVisitedOnlyOnce() {
        List<Integer> result = ChessPathService.findPath(generateTile());

        assertEquals(result.size(), result.stream().distinct().count());
    }
}
