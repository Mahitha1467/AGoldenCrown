package input;

import exception.UnexpectedFormatException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(PowerMockRunner.class)
public class InputParserTest {

    private InputParser parser;

    @Before
    public void setUp() throws Exception {
        parser = new InputParser();
    }

    @Test
    public void shouldReturnTrueIfTheGivenInputIsEmpty() throws UnexpectedFormatException {
        String input = "";
        boolean isValid = parser.isFormatValid(input);
        assertTrue(isValid);
    }

    @Test
    public void shouldThrowExceptionIfTheGivenInputMoreThanOneComma() {
        String input = "Air, asdff, wert";
        String expectedMessage = "Input should not have only one Comma. Expected format is 'Air, \"qwert\"'";

        try {
            parser.isFormatValid(input);
        } catch (UnexpectedFormatException e) {
            assertEquals(expectedMessage, e.getMessage());
        }
    }

    @Test
    public void shouldThrowExceptionIfTheGivenInputDoesNotHaveExpectedKingdoms() {
        String input = "Qwe, \"some\"";
        String expectedMessage = "Input should have only Air, Ice, Fire, Land, Water Kingdoms";

        try {
            parser.isFormatValid(input);
        } catch (UnexpectedFormatException e) {
            assertEquals(expectedMessage, e.getMessage());
        }
    }

    @Test
    public void shouldReturnTrueIfTheGivenFormatIsCorrect() throws UnexpectedFormatException {
        String input = "Air, \"some\"";
        boolean isValid = parser.isFormatValid(input);
        assertTrue(isValid);
    }

    @Test
    public void shouldReturnEmptyListIfTheGivenListIsEmpty() {
        List<String> list = parser.removeLastIndex(Collections.EMPTY_LIST);

        assertEquals(0, list.size());
    }

    @Test
    public void shouldReturnTheSubListByRemovingLastIndex() {
        List<String> givenList = Arrays.asList("first", "second");
        List<String> expectedList = Collections.singletonList("first");

        List<String> actualList = parser.removeLastIndex(givenList);

        assertEquals(1, actualList.size());
        assertEquals(expectedList, actualList);
    }

    @Test
    public void shouldReturnFalseIfTheGivenListSizeIsBelow3() {
        assertFalse(parser.isInputsSufficientToDeclareRuler(Arrays.asList("first", "second")));
    }

    @Test
    public void shouldReturnTrueIfTheGivenListSizeIsEqualTo3() {
        assertTrue(parser.isInputsSufficientToDeclareRuler(Arrays.asList("first", "second", "third")));
    }

    @Test
    public void shouldReturnTrueIfTheGivenListSizeIsEqualGreaterThan3() {
        assertTrue(parser.isInputsSufficientToDeclareRuler(Arrays.asList("first", "second", "third", "fourth")));
    }
}
