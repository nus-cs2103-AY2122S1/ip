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

    // Error message formats.
    private static final String EMPTY_COMMAND_ERROR_MESSAGE = "Instruction cannot be empty.";
    private static final String NOT_AN_INTEGER_ERROR_MESSAGE = "'%s' is not an integer.";
    private static final String UNKNOWN_COMMAND_ERROR_MESSAGE =
            "Instruction does not follows specified format.";
    private static final String EMPTY_PARAM_TO_COMMAND_ERROR_MESSAGE =
            "Command '%s' requires parameters to execute.";
    private static final String IMPROPER_FORMATTED_ERROR_MESSAGE =
            "Command '%s' is not formatted properly with key '%s'";

    /**
     * This method parses the inputs according to the command specified,
     * and then executes the command using the taskManager. An output message will
     * be returned.
     *
     * @param command command order to execute
     * @param userInput user input to parse for execution
     * @param taskManager executor of tasks based on commands and parameters
     * @return output String message from taskManager after execution
     * @throws DukeException if userInput is of invalid format,
     * or taskManager is unable to execute command
     */
    public String execute(Command command, String userInput, TaskManager taskManager)
            throws DukeException {

        assert (!command.equals(Command.BYE));

        switch (command) {
            case LIST:
                return taskManager.getTaskList();
            case DONE:
                return taskManager.updateTaskAsDone(parseToNumber(userInput, command));
            case DELETE:
                return taskManager.deleteTask(parseToNumber(userInput, command));
            case TODO:
                return taskManager.addToDoTask(parseToString(userInput, command));
            case EVENT:
                return taskManager.addEventTask(parseToStringArray(userInput, command));
            case DEADLINE:
                return taskManager.addDeadlineTask(parseToStringArray(userInput, command));
            case EMPTY:
                throw new DukeException(EMPTY_COMMAND_ERROR_MESSAGE);
            default: // INVALID
                throw new DukeException(UNKNOWN_COMMAND_ERROR_MESSAGE);
        }
    }

    /**
     * Returns the parameters of a user input based on command.
     *
     * @param userInput user input
     * @param command command to be executed with specified arrangement of parameters
     * @return parameter String
     * @throws DukeException if there are no parameters for the command
     */
    public String extractParameter(String userInput, Command command) throws DukeException {
        try {
            String[] parameters = userInput.trim().split(" ", 2); // split on first occurrence
            return parameters[1]; // grab second word onwards
        } catch (ArrayIndexOutOfBoundsException exception) {
            throw new DukeException(String.format(EMPTY_PARAM_TO_COMMAND_ERROR_MESSAGE, command));
        }
    }

    /**
     * Parses the user's input to return a String parameter for the command.
     *
     * @param userInput user input
     * @param command command to be executed with specified arrangement of parameters
     * @return a String parameter
     * @throws DukeException if userInput is of invalid format
     */
    public String parseToString(String userInput, Command command) throws DukeException {
        String userParam = extractParameter(userInput, command);

        if (userParam.isBlank()) {
            throw new DukeException(String.format(EMPTY_PARAM_TO_COMMAND_ERROR_MESSAGE, command));
        }
        return userParam.trim();
    }

    /**
     * Parses the user's input to return a String array for the command.
     *
     * @param userInput user input
     * @param command command to be executed with specified arrangement of parameters
     * @return a String array of parameters
     * @throws DukeException if userInput is of invalid format
     */
    public String[] parseToStringArray(String userInput, Command command) throws DukeException {
        String userParams = extractParameter(userInput, command);

        String key = "/";
        if (command.equals(Command.EVENT)) {
            key += Event.SPLIT_WORD;
        } else if (command.equals(Command.DEADLINE)) {
            key += Deadline.SPLIT_WORD;
        }

        String[] parameters = userParams.split(key, 0); // pattern applied many times as possible
        if (parameters.length != 2) {
            throw new DukeException(String.format(IMPROPER_FORMATTED_ERROR_MESSAGE, command, key));
        } else if (parameters[0].isBlank() || parameters[1].isBlank()) {
            throw new DukeException(String.format(EMPTY_PARAM_TO_COMMAND_ERROR_MESSAGE, command));
        }
        return new String[] {parameters[0].trim(), parameters[1].trim()}; // desc, detail
    }

    /**
     * Parses the user's input to return a String array for the command.
     *
     * @param userInput user input
     * @param command command to be executed with specified arrangement of parameters
     * @return an integer parameter
     * @throws DukeException if userInput is of invalid format
     */
    public int parseToNumber(String userInput, Command command) throws DukeException {
        String userParam = extractParameter(userInput, command);

        try {
            return Integer.parseInt(userParam);
        } catch (NumberFormatException exception) {
            throw new DukeException(String.format(NOT_AN_INTEGER_ERROR_MESSAGE, userParam));
        }
    }
}
