package dino.util;

import dino.command.*;
import dino.exception.*;

/**
 * Deals with making sense of the user command
 */
public class Parser {

    /**
     * Interprets the user input command and parses into corresponding Commands
     * which can then be understood and executed by the ChatBot
     *
     * @param input the input command entered by the user
     * @return a specific command object which corresponds to the user input command
     * @throws InvalidInputException if the user enters a command that doesn't fit the required pattern
     * and thus cannot be understood
     */
    public static Command parse(String input) throws InvalidInputException {
        String type = getFirstWord(input);
        if (type.equals("todo") || type.equals("deadline") || type.equals("event")) {
            Command.CMDTYPE taskType = Command.CMDTYPE.valueOf(type.toUpperCase());
            return new AddTaskCommand(input, taskType);
        } else if (type.equals("done") || type.equals("delete")) {
            Command.CMDTYPE markType = Command.CMDTYPE.valueOf(type.toUpperCase());
            return new MarkCommand(input, markType);
        } else if (type.equals("list")) {
            return new ListCommand();
        } else if (type.equals("find")) {
            return new FindCommand(input);
        } else {
            throw new InvalidInputException();
        }
    }

    /**
     * Returns the first word of an input string
     * Returns the string itself if it contains only one word
     *
     * @param s the input string
     */
    public static String getFirstWord(String s) {
        String firstWord;
        if (s.contains(" ")) {
            firstWord = s.substring(0, s.indexOf(" "));
            return firstWord;
        }
        return s;
    }

}
