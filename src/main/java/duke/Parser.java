package duke;

import java.util.Scanner;

import command.Command;
import exceptions.NoSuchCommandException;


/**
 * The Parser class encapsulates the parsing of user input into commands for Duke.
 *
 * @author Quan Teng Foong
 */
public class Parser {
    /**
     * Parses a string of user input into a command and an input string.
     *
     * @param userInput String input by the user
     * @return a String array holding the command and the input
     */
    public static Command parse(String userInput) throws NoSuchCommandException {
        Scanner sc = new Scanner(userInput);
        String command = sc.next();
        String input = userInput.replace(command, "").strip();
        return Command.of(command, input);
    }
}
