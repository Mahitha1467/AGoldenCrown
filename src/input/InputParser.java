package input;

import exception.UnexpectedFormatException;
import util.KingdomUtil;

import java.util.ArrayList;
import java.util.List;

public class InputParser {
    private final static String INPUT_SEPARATOR = ",";
    private final static int KINGDOM_NAME_INDEX = 0;
    private final static int WORDS_IN_INPUT = 2;
    private final static String EMPTY_STRING = "";
    private final static int ZERO = 0;
    private final static int MIN_INPUT_SIZE = 3;

    boolean isFormatValid(String input) throws UnexpectedFormatException {
        if (EMPTY_STRING.equals(input)) {
            return true;
        }

        String[] splitInput = input.split(INPUT_SEPARATOR);
        if (splitInput.length != WORDS_IN_INPUT) {
            throw new UnexpectedFormatException("Input should not have only one Comma. Expected format is 'Air, \"qwert\"'");
        }

        List<String> kingdomNames = KingdomUtil.getAllKingdomNames();
        if (!kingdomNames.contains(splitInput[KINGDOM_NAME_INDEX].toLowerCase())) {
            throw new UnexpectedFormatException("Input should have only Air, Ice, Fire, Land, Water Kingdoms");
        }

        return true;
    }

    List<String> removeLastIndex(List<String> list) {
        if (list.size() == ZERO) {
            return new ArrayList<>();
        }

        int lastIndex = list.size() - 1;
        return list.subList(ZERO, lastIndex);
    }

    boolean isInputsSufficientToDeclareRuler(List<String> validInputs) {
        return validInputs.size() >= MIN_INPUT_SIZE;
    }
}
