package jared.parser;

import jared.common.DukeException;

/**
 * Parses user input.
 */
public class Parser {

    /**
     * Parses user input into command.
     * @param s user input string.
     * @return the command in user input.
     */
    public static String parseCommand(String s) {
        String[] inputArr = s.split(" ", 2);
        String command = inputArr[0];
        return command;
    }

    /**
     * Parses user input into specified data required.
     *
     * @param s user input string.
     * @param output data output required.
     * @return the required data specified.
     * @throws DukeException if data cannot be parsed.
     */
    public static String parseData(String s, String output) throws DukeException {
        String[] dataArr = s.split(" _ ");
        try {
            switch (output) {
            case "type":
                return dataArr[0];
            case "progress":
                return dataArr[1];
            case "description":
                return dataArr[2];
            case "date":
                return dataArr[3];
            case "time":
                return dataArr[4];
            default:
                throw new DukeException("Unable to process the command");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Unable to parse data");
        }
    }

}
