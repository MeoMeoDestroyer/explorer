import static org.junit.Assert.*;
import org.junit.Test;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ExplorerSearchTest {
    @Test
    public void testReachableArea_someUnreachable() {
        int[][] island = {
            {1,1,1,3,1,1},
            {3,2,3,1,3,1},
            {1,1,1,1,3,3},
            {3,1,2,1,0,1},
            {1,1,1,2,1,1},
        };
        int actual = ExplorerSearch.reachableArea(island);
        assertEquals(14, actual);
    }
    @Test
    public void reachableArea_onlyStart() {
        int[][] island = { {0} };
        assertEquals(1, ExplorerSearch.reachableArea(island));
    }

    @Test
    public void reachableArea_trapped() {
        int[][] island = {
            {1, 2, 1},
            {3, 0, 3},
            {1, 2, 1}
        };
        assertEquals(1, ExplorerSearch.reachableArea(island));
    }

    @Test
    public void reachableArea_allLand() {
        int[][] island = {
            {1, 1, 1, 1},
            {1, 1, 0, 1},
            {1, 1, 1, 1}
        };
        assertEquals(12, ExplorerSearch.reachableArea(island));
    }

    @Test
    public void reachableArea_noDiagonal() {
        int[][] island = {
            {1, 2, 1},
            {2, 0, 2},
            {1, 2, 1}
        };
        assertEquals(1, ExplorerSearch.reachableArea(island));
    }



    // test for exploreer location
    @Test
    public void explorerLocation_middle() {
        int[][] island = { {1,1,1}, {1,0,1}, {1,1,1} };
        assertArrayEquals(new int[]{1, 1}, ExplorerSearch.explorerLocation(island));
    }

    @Test
    public void explorerLocation_corner() {
        int[][] island = { {1,1,1}, {1,1,1}, {1,1,0} };
        assertArrayEquals(new int[]{2, 2}, ExplorerSearch.explorerLocation(island));
    }

    @Test
    public void explorerLocation_noStart() {
        int[][] island = { {1,2}, {3,1} };
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> ExplorerSearch.explorerLocation(island));
        assertEquals("There's no explorer on the island", e.getMessage());
    }

    // tests for isWalkable
    @Test
    public void isWalkable_land() {
        assertTrue(ExplorerSearch.isWalkable(0));
        assertTrue(ExplorerSearch.isWalkable(1));
    }

    @Test
    public void isWalkable_blocked() {
        assertFalse(ExplorerSearch.isWalkable(2));
        assertFalse(ExplorerSearch.isWalkable(3));
    }

    // tests for possibleMoves
    @Test
    public void possibleMoves_allDirectionsOpen() {
        int[][] island = {
            {1, 1, 1},
            {1, 0, 1},
            {1, 1, 1}
        };
        List<int[]> moves = ExplorerSearch.possibleMoves(island, new int[]{1, 1});
        Set<String> moveSet = toSet(moves);

        assertEquals(4, moves.size());

        // up down left right
        assertTrue(moveSet.contains("0,1"));
        assertTrue(moveSet.contains("2,1"));
        assertTrue(moveSet.contains("1,0"));
        assertTrue(moveSet.contains("1,2"));
    }

    @Test
    public void possibleMoves_blockedAboveByWater() {
        int[][] island = {
            {1, 2, 1},
            {1, 0, 1},
            {1, 1, 1}
        };
        Set<String> moveSet = toSet(ExplorerSearch.possibleMoves(island, new int[]{1, 1}));
        // up got block, down left right
        assertFalse(moveSet.contains("0,1"));
        assertTrue(moveSet.contains("2,1"));
        assertTrue(moveSet.contains("1,0"));
        assertTrue(moveSet.contains("1,2"));
    }
    @Test
    public void possibleMoves_blockedRightByMountain() {
        int[][] island = {
            {1, 1, 1},
            {1, 0, 3},
            {1, 1, 1}
        };
        Set<String> moveSet = toSet(ExplorerSearch.possibleMoves(island, new int[]{1, 1}));
        // up down left, right got block
        assertTrue(moveSet.contains("0,1")); 
        assertTrue(moveSet.contains("2,1"));
        assertTrue(moveSet.contains("1,0"));
        assertFalse(moveSet.contains("1,2"));
    }

    @Test
    public void possibleMoves_blockedBelowByMountain() {
        int[][] island = {
            {1, 1, 1},
            {1, 0, 1},
            {1, 3, 1}
        };
        Set<String> moveSet = toSet(ExplorerSearch.possibleMoves(island, new int[]{1, 1}));
        // up, down got block, left right
        assertTrue(moveSet.contains("0,1"));
        assertFalse(moveSet.contains("2,1"));
        assertTrue(moveSet.contains("1,0"));
        assertTrue(moveSet.contains("1,2"));
    }

    @Test
    public void possibleMoves_blockedLeftByWater() {
        int[][] island = {
            {1, 1, 1},
            {2, 0, 1},
            {1, 1, 1}
        };
        Set<String> moveSet = toSet(ExplorerSearch.possibleMoves(island, new int[]{1, 1}));
        // left got block, up down right
        assertTrue(moveSet.contains("0,1"));
        assertTrue(moveSet.contains("2,1"));
        assertFalse(moveSet.contains("1,0"));
        assertTrue(moveSet.contains("1,2"));
    }

    @Test
    public void possibleMoves_topLeftCorner() {
        int[][] island = {
            {0, 1},
            {1, 1}
        };
        List<int[]> moves = ExplorerSearch.possibleMoves(island, new int[]{0, 0});
        Set<String> moveSet = toSet(moves);

        assertEquals(2, moves.size());
        // down, right
        assertTrue(moveSet.contains("1,0"));
        assertTrue(moveSet.contains("0,1"));
    }
    @Test
    public void possibleMoves_allBlocked() {
        int[][] island = {
            {3, 2, 3},
            {2, 0, 3},
            {3, 2, 3}
        };
        assertTrue(ExplorerSearch.possibleMoves(island, new int[]{1, 1}).isEmpty());
    }

    @Test
    public void possibleMoves_noDiagonal() {
        int[][] island = {
            {1, 2, 1},
            {2, 0, 2},
            {1, 2, 1}
        };
        assertTrue(ExplorerSearch.possibleMoves(island, new int[]{1, 1}).isEmpty());
    }

    @Test
    public void possibleMoves_bottomRightCorner() {
        int[][] island = {
            {1, 1},
            {1, 0}
        };
        List<int[]> moves = ExplorerSearch.possibleMoves(island, new int[]{1, 1});
        Set<String> moveSet = toSet(moves);

        assertEquals(2, moves.size());
        // up left
        assertTrue(moveSet.contains("0,1"));
        assertTrue(moveSet.contains("1,0"));
    }




        private Set<String> toSet(List<int[]> list) {
        Set<String> set = new HashSet<>();
        for (int[] arr : list) {
            set.add(arr[0] + "," + arr[1]);
        }
        return set;
    }
    
}