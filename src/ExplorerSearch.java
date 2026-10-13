import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ExplorerSearch {

    /**
     * Returns how much land area an explorer can reach on a rectangular island.
     * 
     * The island is represented by a rectangular int[][] that contains
     * ONLY the following nunbers:
     * 
     * '0': represents the starting location of the explorer
     * '1': represents a field the explorer can walk through
     * '2': represents a body of water the explorer cannot cross
     * '3': represents a mountain the explorer cannot cross
     * 
     * The explorer can move one square at a time: up, down, left, or right.
     * They CANNOT move diagonally.
     * They CANNOT move off the edge of the island.
     * They CANNOT move onto a a body of water or mountain.
     * 
     * This method should return the total number of spaces the explorer is able
     * to reach from their starting location. It should include the starting
     * location of the explorer.
     * 
     * For example
     * 
     * @param island the locations on the island
     * @return the number of spaces the explorer can reach
     */
    public static int reachableArea(int[][] island) {
        int[] start = explorerLocation(island);
        boolean[][] visited = new boolean[island.length][island[0].length];
        return reachableArea(island, start, visited);
    }

    // counts this square plus every square reachable from it
    private static int reachableArea(int[][] island, int[] current, boolean[][] visited) {
        int curR = current[0];
        int curC = current[1];

        // already counted, don't count again
        if (visited[curR][curC]) return 0;
        visited[curR][curC] = true;

        int count = 1;
        for (int[] move : possibleMoves(island, current)) {
            count += reachableArea(island, move, visited);
        }
        return count;
    }

    // find the explorer starting location
    public static int[] explorerLocation(int[][] island) {
        for (int r = 0; r < island.length; r++) {
            for (int c = 0; c < island[r].length; c++) {
                if (island[r][c] == 0) return new int[] {r, c};
            }
        }
        // if there is no explorer -> throw
        throw new IllegalArgumentException("There's no explorer on the island");
    }

    // true if the explorer can stand here start or field
    // false if on water or mountain
    public static boolean isWalkable(int value) {
        return value == 0 || value == 1;
    }

    // only up down left right, no diagonal or off the edge
    public static List<int[]> possibleMoves(int[][] island, int[] current) {
        int curR = current[0];
        int curC = current[1];
        List<int[]> possible = new ArrayList<>();

        // up
        if (curR - 1 >= 0 && isWalkable(island[curR - 1][curC])) {
            possible.add(new int[] {curR - 1, curC});
        }

        // down
        if (curR + 1 < island.length && isWalkable(island[curR + 1][curC])) {
            possible.add(new int[] {curR + 1, curC});
        }

        // left
        if (curC - 1 >= 0 && isWalkable(island[curR][curC - 1])) {
            possible.add(new int[] {curR, curC - 1});
        }

        // right
        if (curC + 1 < island[curR].length && isWalkable(island[curR][curC + 1])) {
            possible.add(new int[] {curR, curC + 1});
        }

        return possible;
    }

}
