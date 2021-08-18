package service;

import exception.DukeException;
import task.Deadline;
import task.Event;
import utils.Command;
import utils.Instruction;

/**
 * Parser class.
 *
 * This class helps parse user input into Instructions for Duke to use with ease.
 */
public class Parser {

    /**
     * Parses user input and provides an Instruction for Duke to use.
     *
     * @param userInput user input to be parsed
     * @return instruction based on input fed
     * @throws DukeException if user input is of invalid format
     */
    public Instruction parseUserInput(String userInput) throws DukeException {
        userInput = userInput.trim();
        if (userInput.isEmpty()) {
            return new Instruction(Command.EMPTY);
        }

        String[] inputArray = userInput.split(" ", 2);
        String inputCommand = inputArray[0];
        String[] strings = new String[] {};
        int number = -1;

        Command command;
        switch (inputCommand) {
            case "bye":
                command = Command.BYE;
                break;
            case "list":
                command = Command.LIST;
                break;
            case "done":
                command = Command.DONE;
                number = parseToNumber(inputArray[1]);
                break;
            case "delete":
                command = Command.DELETE;
                number = parseToNumber(inputArray[1]);
                break;
            case "todo":
                command = Command.TODO;
                strings = parseToString(inputArray[1], command);
                break;
            case "event":
                command = Command.EVENT;
                strings = parseToString(inputArray[1], command);
                break;
            case "deadline":
                command = Command.DEADLINE;
                strings = parseToString(inputArray[1], command);
                break;
            default: // unknown parameters
                command = Command.UNKNOWN;
        }
        return new Instruction(command, strings, number);
    }

    /**
     * Parses the user parameters into a String array for use by Duke.
     *
     * @param userParams user parameters to parse
     * @param command command to be applied with
     * @return a String array of parameters
     * @throws DukeException if userParams is of invalid format
     */
    public String[] parseToString(String userParams, Command command) throws DukeException {
        userParams = userParams.trim();
        if (userParams.isEmpty()) {
            throw new DukeException("");
        }

        if (command.equals(Command.TODO)) {
            return new String[]{userParams};
        }

        String splitter = " ";
        if (command.equals(Command.EVENT)) {
            splitter = Event.SPLITTER;
        }
        if (command.equals(Command.DEADLINE)) {
            splitter = Deadline.SPLITTER;
        }
        String[] stringArray = userParams.split(splitter);
        String desc = stringArray[0].trim();
        String param = stringArray[1].trim();
        if (desc.isEmpty() || param.isEmpty()) {
            throw new DukeException("");
        }
        return new String[] {desc, param};
    }

    /**
     * Parses the string to an integer.
     *
     * @param userParams string to parse to integer
     * @return an integer
     * @throws DukeException if userParam cannot be parsed to an integer
     */
    public int parseToNumber(String userParams) throws DukeException {
        try {
            return Integer.parseInt(userParams.trim());
        } catch (NumberFormatException exception) {
            throw new DukeException("");
        }

    }
}
