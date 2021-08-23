package service;

import exception.DukeException;
import task.Deadline;
import task.Event;
import utils.Command;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

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
     * @param taskList executor of tasks based on commands and parameters
     * @return output String message from taskManager after execution
     * @throws DukeException if userInput is of invalid format,
     * or taskManager is unable to execute command
     */
    public String execute(Command command, String userInput, TaskList taskList)
            throws DukeException {

        assert (!command.equals(Command.BYE));
        String desc;
        String[] dateTime;
        int taskNumber;
        LocalDate date;
        LocalTime time;
        
        switch (command) {
            case LIST:
                return taskList.getTaskList();
            case DONE:
                taskNumber = extractNumber(userInput, command);
                return taskList.updateTaskAsDone(taskNumber);
            case DELETE:
                taskNumber = extractNumber(userInput, command);
                return taskList.deleteTask(taskNumber);
            case TODO:
                desc = extractDesc(userInput, command);
                return taskList.addToDoTask(desc);
            case EVENT:
                desc = extractDesc(userInput, command);
                dateTime = extractDateTime(userInput, command);
                date = extractDate(dateTime);
                time = extractTime(dateTime);
                return taskList.addEventTask(desc, date, time);
            case DEADLINE:
                desc = extractDesc(userInput, command);
                dateTime = extractDateTime(userInput, command);
                date = extractDate(dateTime);
                time = extractTime(dateTime);
                return taskList.addDeadlineTask(desc, date, time);
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
    public String extractParameters(String userInput, Command command) throws DukeException {
        try {
            String[] parameters = userInput.split(" ", 2); // split on first occurrence
            return parameters[1].trim(); // grab second word onwards
        } catch (ArrayIndexOutOfBoundsException exception) {
            throw new DukeException(String.format(EMPTY_PARAM_TO_COMMAND_ERROR_MESSAGE, command));
        }
    }

    /**
     * DEPRECIATE
     * Parses the user's input to return a String parameter for the command.
     *
     * @param userInput user input
     * @param command command to be executed with specified arrangement of parameters
     * @return a String parameter
     * @throws DukeException if userInput is of invalid format
     */
    public String parseToString(String userInput, Command command) throws DukeException {
        String userParam = extractParameters(userInput, command);

        if (userParam.isBlank()) {
            throw new DukeException(String.format(EMPTY_PARAM_TO_COMMAND_ERROR_MESSAGE, command));
        }
        return userParam;
    }

    /**
     * Parses the user's input to return a String array for the command.
     *
     * @param userInput user input
     * @param command command to be executed with specified arrangement of parameters
     * @return a String array of parameters
     * @throws DukeException if userInput is of invalid format
     */
    public String extractDesc(String userInput, Command command) throws DukeException {
        String userParams = extractParameters(userInput, command);

        String key = "/";
        if (command.equals(Command.EVENT)) {
            key += Event.SPLIT_WORD;
        } else if (command.equals(Command.DEADLINE)) {
            key += Deadline.SPLIT_WORD;
        }

        String[] parameters = userParams.split(key); // pattern applied many times as possible
        if (parameters.length < 1 || parameters[0].isBlank()) {
            throw new DukeException(String.format(IMPROPER_FORMATTED_ERROR_MESSAGE, command, key));
        }
        return parameters[0].trim();
    }

    public String[] extractDateTime(String userInput, Command command) throws DukeException {
        String key = "/";
        if (command.equals(Command.EVENT)) {
            key += Event.SPLIT_WORD;
        } else if (command.equals(Command.DEADLINE)) {
            key += Deadline.SPLIT_WORD;
        }
        String[] parameters = userInput.split(key, 0); // pattern applied many times as possible
        if (parameters.length != 2) {
            throw new DukeException(String.format(IMPROPER_FORMATTED_ERROR_MESSAGE, command, key));
        }
        String[] dateTime = parameters[1].trim().split(" ", 2);
        if (dateTime.length != 1 
                && dateTime[0].isBlank() 
                && dateTime.length != 2 
                && dateTime[1].isBlank()) {
            throw new DukeException(String.format(IMPROPER_FORMATTED_ERROR_MESSAGE, command, key));
        }
        return dateTime;
    }
    
    public LocalDate extractDate(String[] dateTime) throws DukeException {
        List<DateTimeFormatter> dateFormatterList = new ArrayList<>();
        dateFormatterList.add(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        dateFormatterList.add(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        dateFormatterList.add(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        dateFormatterList.add(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        dateFormatterList.add(DateTimeFormatter.ofPattern("dd/M/yyyy"));
        dateFormatterList.add(DateTimeFormatter.ofPattern("yyyy/M/dd"));
        dateFormatterList.add(DateTimeFormatter.ofPattern("dd-M-yyyy"));
        dateFormatterList.add(DateTimeFormatter.ofPattern("yyyy-M-dd"));

        dateFormatterList.add(DateTimeFormatter.ofPattern("dd/MMMM/yyyy"));
        dateFormatterList.add(DateTimeFormatter.ofPattern("yyyy/MMMM/dd"));
        dateFormatterList.add(DateTimeFormatter.ofPattern("dd-MMMM-yyyy"));
        dateFormatterList.add(DateTimeFormatter.ofPattern("yyyy-MMMM-dd"));

        dateFormatterList.add(DateTimeFormatter.ofPattern("dd.MMMM.yyyy"));
        dateFormatterList.add(DateTimeFormatter.ofPattern("yyyy.MMMM.dd"));
        dateFormatterList.add(DateTimeFormatter.ofPattern("dd.MMMM.yyyy"));
        dateFormatterList.add(DateTimeFormatter.ofPattern("yyyy.MMMM.dd"));
        
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
        
        List<DateTimeFormatter> timeFormatterList = new ArrayList<>();
        timeFormatterList.add(DateTimeFormatter.ofPattern("HH:mm"));
        timeFormatterList.add(DateTimeFormatter.ofPattern("hh:mm a"));
        
        timeFormatterList.add(DateTimeFormatter.ofPattern("HH:mm:ss"));
        timeFormatterList.add(DateTimeFormatter.ofPattern("hh:mm:ss a"));
        
        timeFormatterList.add(DateTimeFormatter.ofPattern("HHmm"));
        timeFormatterList.add(DateTimeFormatter.ofPattern("hhmm a"));

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
     * @param command command to be executed with specified arrangement of parameters
     * @return an integer parameter
     * @throws DukeException if userInput is of invalid format
     */
    public int extractNumber(String userInput, Command command) throws DukeException {
        String userParam = extractParameters(userInput, command);

        try {
            return Integer.parseInt(userParam);
        } catch (NumberFormatException exception) {
            throw new DukeException(String.format(NOT_AN_INTEGER_ERROR_MESSAGE, userParam));
        }
    }
}
