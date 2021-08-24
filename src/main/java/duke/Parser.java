package duke;

import java.util.Scanner;

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
    public static String[] parse(String userInput) {
        Scanner sc = new Scanner(userInput);
        String command = sc.next();
        String input = userInput.replace(command, "").strip();
        return new String[]{command, input};
    }
}
