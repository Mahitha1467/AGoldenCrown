import model.Input;
import model.Ruler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static util.KingdomUtil.getKingdomEmblems;
import static util.StringUtil.ZERO;
import static util.StringUtil.getFrequencyOfChar;
import static util.StringUtil.getUniqueCharsIgnoreCase;

public class SouthErosRuler {

    public Ruler getRulerWithAllies(List<Input> inputs) {
        List<String> inputKingdomsNames = getInputKingdoms(inputs);
        Map<String, Map<Character, Integer>> kingdomsWithEmblemFrequencies = getKingdomsWithEmblemFrequencies(inputKingdomsNames);
        List<String> allies = checkForAllies(inputs, kingdomsWithEmblemFrequencies);

        if (allies.size() >= 3) {
            return new Ruler("King Shan", allies);
        }
        return new Ruler();
    }

    private List<String> getInputKingdoms(List<Input> inputs) {
        return inputs.stream()
                .map(Input::getKingdomName)
                .collect(Collectors.toList());
    }

    private Map<String, Map<Character, Integer>> getKingdomsWithEmblemFrequencies(List<String> kingdomsNames) {
        Map<String, String> kingdomWithEmblems = getKingdomEmblems(kingdomsNames);
        Map<String, Map<Character, Integer>> kingdomsWithEmblemFrequencies = new HashMap<>();
        kingdomWithEmblems.keySet()
                .forEach(kingdomName -> {
                    kingdomsWithEmblemFrequencies.put(kingdomName,
                            getFrequencyOfUniqueChars(kingdomWithEmblems.get(kingdomName)));
                });

        return kingdomsWithEmblemFrequencies;
    }

    private Map<Character, Integer> getFrequencyOfUniqueChars(String emblem) {
        Set<Character> uniqueCharsOfEmblem = getUniqueCharsIgnoreCase(emblem);
        Map<Character, Integer> frequencyOfUniqueCharsOfEmblem = new HashMap<>();

        uniqueCharsOfEmblem.forEach(uniqueChar -> {
            frequencyOfUniqueCharsOfEmblem.put(uniqueChar, getFrequencyOfChar(emblem, uniqueChar));
        });

        return frequencyOfUniqueCharsOfEmblem;
    }

    private List<String> checkForAllies(List<Input> inputs, Map<String, Map<Character, Integer>> kingdomsWithEmblemFrequencies) {
        List<String> allies = new ArrayList<>();
        inputs.forEach(input -> {
            String kingdomName = input.getKingdomName();
            Map<Character, Integer> emblemFrequency = kingdomsWithEmblemFrequencies.get(kingdomName);

            if(isKingdomSupports(input.getMessage(), emblemFrequency)) {
                allies.add(kingdomName);
            }
        });

        return allies;
    }

    private boolean isKingdomSupports(String message, Map<Character, Integer> emblemFrequency) {
        if(message.length() < emblemFrequency.keySet().size()) {
            return false;
        }
        return emblemFrequency.keySet()
                .stream()
                .allMatch(character ->
                        emblemFrequency.get(character).compareTo(getFrequencyOfChar(message, character)) <= ZERO
                );
    }
}
