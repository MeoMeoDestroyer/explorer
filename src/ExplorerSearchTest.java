import static org.junit.Assert.*;
import org.junit.Test;

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


}
