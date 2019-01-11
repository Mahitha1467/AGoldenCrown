import input.ConsoleInput;
import input.InputParser;
import model.Input;
import model.Ruler;

import java.util.List;
import java.util.Scanner;

public class MainClass {
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        InputParser parser = new InputParser();
        ConsoleInput consoleInput = new ConsoleInput(scanner, parser);
        SouthErosRuler southErosRuler = new SouthErosRuler();

        List<String> validInputs = consoleInput.getValidInputs();
        List<Input> parsedInputs = parser.parse(validInputs);
        Ruler rulerWithAllies = southErosRuler.getRulerWithAllies(parsedInputs);
    }
}
