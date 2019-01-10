package util;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class KingdomUtilTest {
    @Test
    public void shouldGetAllTheKingdomNames() {
        List<String> expected = Arrays.asList("land", "water", "ice", "air", "fire");

        List<String> actual = KingdomUtil.getAllKingdomNames();

        assertEquals(expected.size(), actual.size());
        assertEquals(expected, actual);
    }
}
