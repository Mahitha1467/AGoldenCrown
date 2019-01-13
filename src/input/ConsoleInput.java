package input;

import exception.UnexpectedFormatException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleInput {
    private static final String EMPTY_STRING = "";

    private Scanner scanner;
    private InputParser parser;

    public ConsoleInput(Scanner scanner, InputParser parser) {
        this.scanner = scanner;
        this.parser = parser;
    }

    public List<String> getValidInputs() {
        List<String> inputs = new ArrayList<>();
        boolean isSufficient;
        System.out.println("Input messages to kingdoms from King Shan. Click on only Enter when you want stop giving input.");

        do {
            getInputsFromUser(inputs);
            isSufficient = parser.isInputsSufficientToDeclareRuler(inputs);
            if (!isSufficient) {
                System.out.println("Please provide sufficient inputs to declare ruler. Minimum size of inputs is 3");
            }
        } while(!isSufficient);

        return inputs;
    }

    private void getInputsFromUser(List<String> inputs) {
        String input;
        do {
            input = scanner.nextLine();
            try {
                parser.isFormatValid(input);
                if (!EMPTY_STRING.equals(input)) {
                    inputs.add(input);
                }
            } catch (UnexpectedFormatException e) {
                System.out.println(e.getMessage());
            }
        } while (!EMPTY_STRING.equals(input));
    }
}
