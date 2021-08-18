package service;

import exception.DukeException;
import task.Deadline;
import task.Event;
import utils.Command;

/**
 * Parser class.
 *
 * This class helps parse user input into Instructions for Duke to use with ease.
 */
public class Parser {

    /** Error messages. */
    private static final String EMPTY_COMMAND_MESSAGE = "Command cannot be empty.";
    private static final String UNKNOWN_COMMAND_MESSAGE = "Instruction does not follows specified format.";
    private static final String EMPTY_PARAM_TO_COMMAND_MESSAGE = "User parameter(s) to command '%s' cannot be empty.";
    private static final String NOT_AN_INTEGER_MESSAGE = "'%s' is not an integer.";

    /**
     * This method parses the inputs according to the command specified,
     * and then executes the command using the taskManager. An output message will
     * be returned.
     *
     * @param command command order to execute
     * @param userInput user input to parse for execution
     * @param taskManager executor of tasks based on commands and parameters
     * @return output message from taskManager after execution
     * @throws DukeException if userInput is of invalid format,
     * or taskManager is unable to execute command
     */
    public String execute(Command command, String userInput, TaskManager taskManager)
            throws DukeException {

        assert (!command.equals(Command.BYE));
        String output;

        // intermediates to parse
        int singleNumber;
        String singleString;
        String[] multipleStrings;

        switch (command) {
            case LIST:
                if (!userInput.equals(command.toString())) {
                    throw new DukeException(UNKNOWN_COMMAND_MESSAGE); // proper 'list' command
                }
                output = taskManager.getTaskList();
                break;
            case DONE:
                singleNumber = parseParameterToNumber(userInput, Command.DONE);
                output = taskManager.markTaskAsDone(singleNumber);
                break;
            case DELETE:
                singleNumber = parseParameterToNumber(userInput, Command.DELETE);
                output = taskManager.deleteTask(singleNumber);
                break;
            case TODO:
                singleString = parseParameterToString(userInput, Command.TODO);
                output = taskManager.addToDoTask(singleString);
                break;
            case EVENT:
                multipleStrings = parseParameterToStringArray(userInput, Command.EVENT);
                output = taskManager.addEventTask(multipleStrings);
                break;
            case DEADLINE:
                multipleStrings = parseParameterToStringArray(userInput, Command.DEADLINE);
                output = taskManager.addDeadlineTask(multipleStrings);
                break;
            default: // INVALID
                if (userInput.isBlank()) {
                    throw new DukeException(EMPTY_COMMAND_MESSAGE);
                }
                throw new DukeException(UNKNOWN_COMMAND_MESSAGE);
        }
        return output;
    }

    /**
     * Parses the user's input to return a String parameter for the command.
     *
     * @param userInput user input to extract and parse parameter
     * @param command command to be executed with specified arrangement of parameters
     * @return a String parameter
     * @throws DukeException if userInput is of invalid format
     */
    public String parseParameterToString(String userInput, Command command) throws DukeException {
        int size = 2;
        String[] userParams = userInput.split(" ", size);
        if (userParams.length < size || userParams[1].isBlank()) {
            throw new DukeException(String.format(EMPTY_PARAM_TO_COMMAND_MESSAGE, command));
        }

        // $ command (WORD-1)
        return userParams[1].trim();
    }

    /**
     * Parses the user's input to return a String array for the command.
     *
     * @param userInput user input to extract and parse parameters
     * @param command command to be executed with specified arrangement of parameters
     * @return a String array of parameters
     * @throws DukeException if userInput is of invalid format
     */
    public String[] parseParameterToStringArray(String userInput, Command command)
            throws DukeException {

        int size = 2;
        String[] parameters = userInput.split(" ", size);
        // $ (command) (WORD-1 /SPLIT WORD-2)
        if (parameters.length < size) {
            throw new DukeException(String.format(EMPTY_PARAM_TO_COMMAND_MESSAGE, command));
        }

        String split = " /";
        if (command.equals(Command.EVENT)) {
            split += Event.SPLIT_WORD;
        } else if (command.equals(Command.DEADLINE)) {
            split += Deadline.SPLIT_WORD;
        }
        split += ' '; // to ensure proper split

        String[] userParams = parameters[1].split(split, size);
        // $ ... (WORD-1) /SPLIT (WORD-2)
        if (userParams.length < size || userParams[0].isBlank() || userParams[1].isBlank()) {
            throw new DukeException(String.format(EMPTY_PARAM_TO_COMMAND_MESSAGE, command));
        }

        // $ ... (WORD-1) ... (WORD-2)
        String desc = userParams[0].trim();
        String detail = userParams[1].trim();
        return new String[] {desc, detail};
    }

    /**
     * Parses the user's input to return a String array for the command.
     *
     * @param userInput user input to extract and parse parameter
     * @param command command to be executed with specified arrangement of parameters
     * @return an integer parameter
     * @throws DukeException if userInput is of invalid format
     */
    public int parseParameterToNumber(String userInput, Command command) throws DukeException {
        int size = 2;
        String[] userParams = userInput.split(" ", size);
        if (userParams.length < size || userParams[0].isBlank()) {
            throw new DukeException(String.format(EMPTY_PARAM_TO_COMMAND_MESSAGE, command));
        }

        // $ command (NUM-1)
        String param = userParams[1].trim();
        try {
            return Integer.parseInt(param);
        } catch (NumberFormatException exception) {
            throw new DukeException(String.format(NOT_AN_INTEGER_MESSAGE, param));
        }
    }
}
