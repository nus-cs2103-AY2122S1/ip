package duke.util;

import duke.command.Command;

/**
 * Parser to take care of parsing input given by the user
 */
public class Parser {
    /**
     * Returns the string following the first word
     *
     * @param firstWord The word you want to exclude
     * @param input The string including the first word
     * @return The remaining string
     */
    public static String getRemainingText(String firstWord, String input) {
        String remainingText = "";
        if (input.length() > firstWord.length() + 1) {
            remainingText = input.substring(firstWord.length() + 1).trim();
        }
        return remainingText;
    }

    /**
     * Parses the input given and returns the appropriate Command.
     *
     * @param input User input given
     * @return A command based on the input given
     * @throws DukeException An exception thrown when initialising the command
     */
    public static Command parse(String input) throws DukeException {
        String[] inputArr = input.split(" ");
        String firstWord = inputArr[0];
        String remainingText = Parser.getRemainingText(firstWord, input);
        return Command.initialiseCommand(firstWord, remainingText);
    }
}
