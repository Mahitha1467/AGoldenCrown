import input.ConsoleInput;
import input.InputParser;

import java.util.List;
import java.util.Scanner;

public class MainClass {
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        InputParser parser = new InputParser();
        ConsoleInput consoleInput = new ConsoleInput(scanner, parser);
        List<String> validInputs = consoleInput.getValidInputs();
    }
}
