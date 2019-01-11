package util;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class KingdomUtilTest {
    @Test
    public void shouldGetAllTheKingdomNames() {
        List<String> expected = Arrays.asList("land", "water", "ice", "air", "fire");

        List<String> actual = KingdomUtil.getAllKingdomNames();

        assertEquals(expected.size(), actual.size());
        assertEquals(expected, actual);
    }

    @Test
    public void shouldHaveEmblemAsEmptyStringWhenTheGivenKingdomIsNotInTheExpectedList() {
        Map<String, String> kingdomEmblems = KingdomUtil.getKingdomEmblems(Collections.singletonList("new Kingdom"));

        assertEquals(1, kingdomEmblems.size());
        assertEquals("", kingdomEmblems.get("new Kingdom"));
    }

    @Test
    public void shouldReturnEmblemForTheGivenKingdomName() {
        List<String> kingdoms = Arrays.asList("water", "ice", "land");
        Map<String, String> expected = new HashMap<>();
        expected.put("water", "octopus");
        expected.put("ice", "mammoth");
        expected.put("land", "panda");
        Map<String, String> kingdomEmblems = KingdomUtil.getKingdomEmblems(kingdoms);

        assertEquals(expected.size(), kingdomEmblems.size());
        expected.keySet().forEach(kingdom -> {
            assertEquals(expected.get(kingdom), kingdomEmblems.get(kingdom));
        });
    }
}
