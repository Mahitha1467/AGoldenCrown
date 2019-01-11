package input;

import exception.UnexpectedFormatException;
import model.Input;
import util.KingdomUtil;

import java.util.List;
import java.util.stream.Collectors;

public class InputParser {
    private final static String INPUT_SEPARATOR = ",";
    private final static int KINGDOM_NAME_INDEX = 0;
    private final static int WORDS_IN_INPUT = 2;
    private final static String EMPTY_STRING = "";
    private final static int ZERO = 0;
    private final static int MIN_INPUT_SIZE = 3;
    private final static int MESSAGE_INDEX = 1;
    public static final String EMPTY_QUOTES = "\"\"";

    boolean isFormatValid(String input) throws UnexpectedFormatException {
        if (EMPTY_STRING.equals(input)) {
            return true;
        }

        String[] splitInput = input.split(INPUT_SEPARATOR);
        if (splitInput.length != WORDS_IN_INPUT) {
            throw new UnexpectedFormatException("Input should have only one Comma. Expected format is 'Air, \"qwert\"'");
        }

        List<String> kingdomNames = KingdomUtil.getAllKingdomNames();
        if (!kingdomNames.contains(splitInput[KINGDOM_NAME_INDEX].toLowerCase())) {
            throw new UnexpectedFormatException("Input should have only Air, Ice, Fire, Land, Water Kingdoms");
        }
        if(EMPTY_QUOTES.equals(splitInput[MESSAGE_INDEX].trim()) || EMPTY_STRING.equals(splitInput[MESSAGE_INDEX].trim())) {
            throw new UnexpectedFormatException("Input should have message");
        }

        return true;
    }

    boolean isInputsSufficientToDeclareRuler(List<String> validInputs) {
        return validInputs.size() >= MIN_INPUT_SIZE;
    }

    public List<Input> parse(List<String> inputs) {
        return inputs.stream().map(input -> {
            String[] split = input.split(INPUT_SEPARATOR);
            return new Input(split[KINGDOM_NAME_INDEX].toLowerCase().trim(), removeQuotes(split[MESSAGE_INDEX]).trim());
        }).collect(Collectors.toList());
    }

    private String removeQuotes(String string) {
        return string.replace("\"", "");
    }
}
