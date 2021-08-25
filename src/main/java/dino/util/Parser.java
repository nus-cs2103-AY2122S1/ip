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
        Command.CMDTYPE type;
        try {
            type= Command.CMDTYPE.valueOf(getFirstWord(input).toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidInputException();
        }
        switch (type) {
            case TODO:
            case EVENT:
            case DEADLINE: {
                return new AddTaskCommand(input, type);
            }
            case DONE:
            case DELETE: {
                return new MarkCommand(input, type);
            }
            case LIST: {
                return new ListCommand();
            }
            case FIND: {
                return new FindCommand(input);
            }
            default: {
                throw new InvalidInputException();
            }
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
