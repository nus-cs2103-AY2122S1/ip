package parser;

import commands.*;
import exception.DukeException;
import service.TaskList;
import task.Deadline;
import task.Event;
import utils.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

/**
 * Parser class.
 *
 * This class helps parse user input into Instructions for Duke to use with ease.
 */
public class Parser {

    private final DateTimeFormat dateTimeFormat = DateTimeFormat.generate();
    
    // Error message formats.
    private static final String EMPTY_COMMAND_ERROR_MESSAGE = "Instruction cannot be empty.";
    private static final String NOT_AN_INTEGER_ERROR_MESSAGE = "'%s' is not an integer.";
    private static final String UNKNOWN_COMMAND_ERROR_MESSAGE =
            "Instruction does not follows specified format.";
    private static final String EMPTY_PARAM_TO_COMMAND_ERROR_MESSAGE =
            "Command '%s' requires parameters to execute.";
    private static final String IMPROPER_FORMATTED_ERROR_MESSAGE =
            "Command '%s' is not formatted properly with key '%s'";
    
    public Command parseCommand(String userInput) {
        try {
            String[] inputs = userInput.split(" ", 2);
            String command = inputs[0];
            switch (command) {
            case ByeCommand.COMMAND_WORD:
                return new ByeCommand();
                
            case ListCommand.COMMAND_WORD:
                return new ListCommand();
                
            case DoneCommand.COMMAND_WORD:
                return new DoneCommand(inputs[1]);
                
            case DeleteCommand.COMMAND_WORD:
                return new DeleteCommand(inputs[1]);
                
            case TodoCommand.COMMAND_WORD:
                return new TodoCommand(inputs[1]);
                
            case EventCommand.COMMAND_WORD:
                return new EventCommand(inputs[1]);
                
            case DeadlineCommand.COMMAND_WORD:
                return new DeadlineCommand(inputs[1]);
                
            default:
                return new InvalidCommand();
            }
        } catch (ArrayIndexOutOfBoundsException exception) {
            return new InvalidCommand();
        }
    }

    /**
     * This method parses the inputs according to the command specified,
     * and then executes the command using the taskManager. An output message will
     * be returned.
     *
     * @param commandType command order to execute
     * @param userInput user input to parse for execution
     * @param taskList executor of tasks based on commands and parameters
     * @return output String message from taskManager after execution
     * @throws DukeException if userInput is of invalid format,
     * or taskManager is unable to execute command
     */
    public String execute(CommandType commandType, String userInput, TaskList taskList)
            throws DukeException {

        assert (!commandType.equals(CommandType.BYE));
        String desc;
        String[] dateTime;
        int taskNumber;
        LocalDate date;
        LocalTime time;
        
        switch (commandType) {
        case LIST:
            return taskList.getTaskList();
        case DONE:
            taskNumber = extractNumber(userInput, commandType);
            return taskList.updateTaskAsDone(taskNumber);
        case DELETE:
            taskNumber = extractNumber(userInput, commandType);
            return taskList.deleteTask(taskNumber);
        case TODO:
            desc = extractDesc(userInput, commandType);
            return taskList.addToDoTask(desc);
        case EVENT:
            desc = extractDesc(userInput, commandType);
            dateTime = extractDateTime(userInput, commandType);
            date = extractDate(dateTime);
            time = extractTime(dateTime);
            return taskList.addEventTask(desc, date, time);
        case DEADLINE:
            desc = extractDesc(userInput, commandType);
            dateTime = extractDateTime(userInput, commandType);
            date = extractDate(dateTime);
            time = extractTime(dateTime);
            return taskList.addDeadlineTask(desc, date, time);
        default:
            throw new DukeException(UNKNOWN_COMMAND_ERROR_MESSAGE);
        }
    }

    /**
     * Returns the parameters of a user input based on command.
     *
     * @param userInput user input
     * @param commandType command to be executed with specified arrangement of parameters
     * @return parameter String
     * @throws DukeException if there are no parameters for the command
     */
    public String extractParameters(String userInput, CommandType commandType) throws DukeException {
        try {
            String[] parameters = userInput.split(" ", 2); // split on first occurrence
            return parameters[1].trim(); // grab second word onwards
        } catch (ArrayIndexOutOfBoundsException exception) {
            throw new DukeException(String.format(EMPTY_PARAM_TO_COMMAND_ERROR_MESSAGE, commandType));
        }
    }

    /**
     * Parses the user's input to return a String array for the command.
     *
     * @param userInput user input
     * @param commandType command to be executed with specified arrangement of parameters
     * @return a String array of parameters
     * @throws DukeException if userInput is of invalid format
     */
    public String extractDesc(String userInput, CommandType commandType) throws DukeException {
        String userParams = extractParameters(userInput, commandType);

        String key = "/";
        if (commandType.equals(CommandType.EVENT)) {
            key += Event.SPLIT_WORD;
        } else if (commandType.equals(CommandType.DEADLINE)) {
            key += Deadline.SPLIT_WORD;
        }
        String[] parameters = userParams.split(key); // pattern applied many times as possible
        if (parameters.length < 1 || parameters[0].isBlank()) {
            throw new DukeException(String.format(IMPROPER_FORMATTED_ERROR_MESSAGE, commandType, key));
        }
        return parameters[0].trim();
    }

    public String[] extractDateTime(String userInput, CommandType commandType) throws DukeException {
        String key = "/";
        if (commandType.equals(CommandType.EVENT)) {
            key += Event.SPLIT_WORD;
        } else if (commandType.equals(CommandType.DEADLINE)) {
            key += Deadline.SPLIT_WORD;
        }
        String[] parameters = userInput.split(key, 0); // pattern applied many times as possible
        if (parameters.length != 2) {
            throw new DukeException(String.format(IMPROPER_FORMATTED_ERROR_MESSAGE, commandType, key));
        }
        String[] dateTime = parameters[1].trim().split(" ", 2);
        if (dateTime.length != 1 
                && dateTime[0].isBlank() 
                && dateTime.length != 2 
                && dateTime[1].isBlank()) {
            throw new DukeException(String.format(IMPROPER_FORMATTED_ERROR_MESSAGE, commandType, key));
        }
        return dateTime;
    }
    
    public LocalDate extractDate(String[] dateTime) throws DukeException {
        List<DateTimeFormatter> dateFormatterList = dateTimeFormat.getDateFormatterList();
        for (DateTimeFormatter formatter: dateFormatterList) {
            try {
                return LocalDate.parse(dateTime[0], formatter);
            } catch (DateTimeParseException exception) {
                // do nothing
            }
        }
        throw new DukeException("Date input is not in the right format.");
    }

    public LocalTime extractTime(String[] dateTime) throws DukeException {
        if (dateTime.length == 1) {
            return null;
        }
        List<DateTimeFormatter> timeFormatterList = dateTimeFormat.getTimeFormatterList();
        String timeString = dateTime[0].toUpperCase();
        for (DateTimeFormatter formatter: timeFormatterList) {
            try {
                return LocalTime.parse(timeString, formatter);
            } catch (DateTimeParseException exception) {
                // do nothing
            }
        }
        throw new DukeException("Time input is not in the right format.");
    }
    
    /**
     * Parses the user's input to return a String array for the command.
     *
     * @param userInput user input
     * @param commandType command to be executed with specified arrangement of parameters
     * @return an integer parameter
     * @throws DukeException if userInput is of invalid format
     */
    public int extractNumber(String userInput, CommandType commandType) throws DukeException {
        String userParam = extractParameters(userInput, commandType);
        try {
            return Integer.parseInt(userParam);
        } catch (NumberFormatException exception) {
            throw new DukeException(String.format(NOT_AN_INTEGER_ERROR_MESSAGE, userParam));
        }
    }
}
