import model.Input;
import model.Ruler;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import util.KingdomUtil;
import util.StringUtil;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.times;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.verifyStatic;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({KingdomUtil.class, StringUtil.class})
public class SouthErosRulerTest {

    private SouthErosRuler southErosRuler;

    @Before
    public void setUp() throws Exception {
        southErosRuler = new SouthErosRuler();

        mockStatic(KingdomUtil.class);
        mockStatic(StringUtil.class);

        Map<String, String> kingdomEmblems = new HashMap<>();
        kingdomEmblems.put("air", "owl");
        kingdomEmblems.put("land", "panda");
        kingdomEmblems.put("ice", "mammoth");

        when(KingdomUtil.getKingdomEmblems(Arrays.asList("air", "land", "ice"))).thenReturn(kingdomEmblems);

        setUpMocksForEmblemUniqueChars();
        setUpMocksForFrequency();
    }

    @Test
    public void shouldReturnEmptyRulerObjectWhenEnoughAlliesAreNotThere() {
        when(StringUtil.getFrequencyOfChar("qwer", 'o')).thenReturn(1);
        when(StringUtil.getFrequencyOfChar("qwer", 'w')).thenReturn(1);
        when(StringUtil.getFrequencyOfChar("qwer", 'l')).thenReturn(1);

        List<Input> givenInputs = Arrays.asList(
          new Input("air", "qwer"),
          new Input("land", "ert"),
          new Input("ice", "zxc")
        );

        Ruler rulerWithAllies = southErosRuler.getRulerWithAllies(givenInputs);

        verifyStatic(times(1));
        KingdomUtil.getKingdomEmblems(Arrays.asList("air", "land", "ice"));

        assertNull(rulerWithAllies.getName());
    }

    @Test
    public void shouldReturnRulerWhenMoreThan3AlliesAreThere() {
        when(StringUtil.getFrequencyOfChar("oaaawaala", 'o')).thenReturn(1);
        when(StringUtil.getFrequencyOfChar("oaaawaala", 'w')).thenReturn(1);
        when(StringUtil.getFrequencyOfChar("oaaawaala", 'l')).thenReturn(1);

        when(StringUtil.getFrequencyOfChar("a1d22n333a4444p", 'p')).thenReturn(1);
        when(StringUtil.getFrequencyOfChar("a1d22n333a4444p", 'a')).thenReturn(2);
        when(StringUtil.getFrequencyOfChar("a1d22n333a4444p", 'n')).thenReturn(1);
        when(StringUtil.getFrequencyOfChar("a1d22n333a4444p", 'd')).thenReturn(1);

        when(StringUtil.getFrequencyOfChar("zmzmzmzaztzozh", 'm')).thenReturn(3);
        when(StringUtil.getFrequencyOfChar("zmzmzmzaztzozh", 'a')).thenReturn(1);
        when(StringUtil.getFrequencyOfChar("zmzmzmzaztzozh", 'o')).thenReturn(1);
        when(StringUtil.getFrequencyOfChar("zmzmzmzaztzozh", 't')).thenReturn(1);
        when(StringUtil.getFrequencyOfChar("zmzmzmzaztzozh", 'h')).thenReturn(1);

        List<Input> givenInputs = Arrays.asList(
                new Input("air", "oaaawaala"),
                new Input("land", "a1d22n333a4444p"),
                new Input("ice", "zmzmzmzaztzozh")
        );

        Ruler rulerWithAllies = southErosRuler.getRulerWithAllies(givenInputs);

        verifyStatic(times(1));
        KingdomUtil.getKingdomEmblems(Arrays.asList("air", "land", "ice"));

        assertEquals("King Shah", rulerWithAllies.getName());
        assertEquals(Arrays.asList("air", "land", "ice"), rulerWithAllies.getAllies());
    }

    @Test
    public void shouldNotConsiderTheCaseWhileComparingTheFrequencyOfTheGivenMessage() {
        List<Input> givenInputs = Arrays.asList(
          new Input("air", "Let’s swing the sword together"),
          new Input("land", "Die or play the tame of thrones"),
          new Input("ice", "Ahoy! Fight for me with men and money"),
          new Input("water", "Summer is coming"),
          new Input("fire", "Drag on Martin!")
        );

        Map<String, String> kingdomEmblems = new HashMap<>();
        kingdomEmblems.put("air", "owl");
        kingdomEmblems.put("land", "panda");
        kingdomEmblems.put("ice", "mammoth");
        kingdomEmblems.put("water", "octo");
        kingdomEmblems.put("fire", "dra");

        Set<Character> octopusUniqueChars = new HashSet<>(Arrays.asList('o', 'c', 't'));
        Set<Character> dragonUniqueChars = new HashSet<>(Arrays.asList('d', 'r', 'a'));

        when(KingdomUtil.getKingdomEmblems(Arrays.asList("air", "land", "ice", "water", "fire")))
                .thenReturn(kingdomEmblems);
        when(StringUtil.getUniqueCharsIgnoreCase("octo")).thenReturn(octopusUniqueChars);
        when(StringUtil.getUniqueCharsIgnoreCase("dra")).thenReturn(dragonUniqueChars);

        when(StringUtil.getFrequencyOfChar("octo", 'o')).thenReturn(2);
        when(StringUtil.getFrequencyOfChar("octo", 'c')).thenReturn(1);
        when(StringUtil.getFrequencyOfChar("octo", 't')).thenReturn(1);

        when(StringUtil.getFrequencyOfChar("dra", 'd')).thenReturn(1);
        when(StringUtil.getFrequencyOfChar("dra", 'r')).thenReturn(1);
        when(StringUtil.getFrequencyOfChar("dra", 'a')).thenReturn(1);

        when(StringUtil.getFrequencyOfChar("Let’s swing the sword together", 'o')).thenReturn(2);
        when(StringUtil.getFrequencyOfChar("Let’s swing the sword together", 'w')).thenReturn(2);
        when(StringUtil.getFrequencyOfChar("Let’s swing the sword together", 'l')).thenReturn(1);

        when(StringUtil.getFrequencyOfChar("Die or play the tame of thrones", 'p')).thenReturn(1);
        when(StringUtil.getFrequencyOfChar("Die or play the tame of thrones", 'a')).thenReturn(2);
        when(StringUtil.getFrequencyOfChar("Die or play the tame of thrones", 'n')).thenReturn(1);
        when(StringUtil.getFrequencyOfChar("Die or play the tame of thrones", 'd')).thenReturn(1);

        when(StringUtil.getFrequencyOfChar("Ahoy! Fight for me with men and money", 'm')).thenReturn(3);
        when(StringUtil.getFrequencyOfChar("Ahoy! Fight for me with men and money", 'a')).thenReturn(2);
        when(StringUtil.getFrequencyOfChar("Ahoy! Fight for me with men and money", 'o')).thenReturn(3);
        when(StringUtil.getFrequencyOfChar("Ahoy! Fight for me with men and money", 't')).thenReturn(1);
        when(StringUtil.getFrequencyOfChar("Ahoy! Fight for me with men and money", 'h')).thenReturn(3);

        when(StringUtil.getFrequencyOfChar("Summer is coming", 'o')).thenReturn(1);
        when(StringUtil.getFrequencyOfChar("Summer is coming", 'c')).thenReturn(1);
        when(StringUtil.getFrequencyOfChar("Summer is coming", 't')).thenReturn(0);

        when(StringUtil.getFrequencyOfChar("Drag on Martin!", 'd')).thenReturn(1);
        when(StringUtil.getFrequencyOfChar("Drag on Martin!", 'r')).thenReturn(2);
        when(StringUtil.getFrequencyOfChar("Drag on Martin!", 'a')).thenReturn(2);

        Ruler rulerWithAllies = southErosRuler.getRulerWithAllies(givenInputs);

        assertEquals("King Shah", rulerWithAllies.getName());
        assertEquals(Arrays.asList("air", "land", "ice", "fire"), rulerWithAllies.getAllies());
    }

    private void setUpMocksForEmblemUniqueChars() {
        Set<Character> owlUniqueChars = new HashSet<>(Arrays.asList('o', 'w', 'l'));
        Set<Character> pandaUniqueChars = new HashSet<>(Arrays.asList('p', 'a', 'n', 'd'));
        Set<Character> mammothUniqueChars = new HashSet<>(Arrays.asList('m', 'a', 'o', 't', 'h'));

        when(StringUtil.getUniqueCharsIgnoreCase("owl")).thenReturn(owlUniqueChars);
        when(StringUtil.getUniqueCharsIgnoreCase("panda")).thenReturn(pandaUniqueChars);
        when(StringUtil.getUniqueCharsIgnoreCase("mammoth")).thenReturn(mammothUniqueChars);
    }

    private void setUpMocksForFrequency() {
        when(StringUtil.getFrequencyOfChar("owl", 'o')).thenReturn(1);
        when(StringUtil.getFrequencyOfChar("owl", 'w')).thenReturn(1);
        when(StringUtil.getFrequencyOfChar("owl", 'l')).thenReturn(1);

        when(StringUtil.getFrequencyOfChar("panda", 'p')).thenReturn(1);
        when(StringUtil.getFrequencyOfChar("panda", 'a')).thenReturn(2);
        when(StringUtil.getFrequencyOfChar("panda", 'n')).thenReturn(1);
        when(StringUtil.getFrequencyOfChar("panda", 'd')).thenReturn(1);

        when(StringUtil.getFrequencyOfChar("mammoth", 'm')).thenReturn(3);
        when(StringUtil.getFrequencyOfChar("mammoth", 'a')).thenReturn(1);
        when(StringUtil.getFrequencyOfChar("mammoth", 'o')).thenReturn(1);
        when(StringUtil.getFrequencyOfChar("mammoth", 't')).thenReturn(1);
        when(StringUtil.getFrequencyOfChar("mammoth", 'h')).thenReturn(1);
    }
}
