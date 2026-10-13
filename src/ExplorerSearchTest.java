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

    
}