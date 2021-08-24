package commands;

import exception.DukeException;
import utils.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public abstract class AddCommand extends Command {

    private final DateTimeFormat dateTimeFormat;

    public AddCommand(CommandType commandType, String commandParams, DateTimeFormat dateTimeFormat) {
        super(commandType, commandParams);
        this.dateTimeFormat = dateTimeFormat;
    }

    public AddCommand(CommandType commandType, String commandParams) {
        super(commandType, commandParams);
        this.dateTimeFormat = null;
    }
    
    protected String[] splitUserParams(String userParams, String splitKey) {
        return userParams.split(" /" + splitKey + ' ');
    }

    protected String extractDesc(String userInput, String commandName, String splitKey) throws DukeException {
        if (splitKey == null) {
            if (userInput.isBlank()) {
                throw new DukeException(String.format(
                        CommandMessage.ERROR_EMPTY_PARAMS, commandName));
            }
            return userInput.trim();
        }
          
        String[] parameters = splitUserParams(userInput, splitKey); // pattern applied many times as possible
        if (parameters.length < 1 || parameters[0].isBlank()) {
            throw new DukeException(String.format(
                    CommandMessage.ERROR_IMPROPER_FORMATTING, commandName, splitKey));
        }
        return parameters[0].trim();
    }

    protected String[] extractDateTime(String userInput, String commandName, String splitKey) throws DukeException {
        if (dateTimeFormat == null || splitKey == null) {
            throw new DukeException(CommandMessage.ERROR_NO_DATETIME_EXPECTED);
        }
        String[] parameters = splitUserParams(userInput, splitKey);
        if (parameters.length != 2) {
            throw new DukeException(String.format(
                    CommandMessage.ERROR_IMPROPER_FORMATTING, commandName, splitKey));
        }
        String[] dateTime = parameters[1].trim().split(" ", 2);
        if (dateTime.length != 2 && dateTime[0].isBlank() && dateTime[1].isBlank()) {
            throw new DukeException(String.format(
                    CommandMessage.ERROR_IMPROPER_FORMATTING, commandName, splitKey));
        }
        return dateTime;
    }

    protected LocalDate extractDate(String[] dateTime) throws DukeException {
        if (dateTimeFormat == null) {
            throw new DukeException(CommandMessage.ERROR_NO_DATETIME_EXPECTED);
        }
        List<DateTimeFormatter> dateFormatterList = dateTimeFormat.getDateFormatterList();
        for (DateTimeFormatter formatter: dateFormatterList) {
            try {
                return LocalDate.parse(dateTime[0], formatter);
            } catch (DateTimeParseException exception) {
                // do nothing
            }
        }
        throw new DukeException(CommandMessage.ERROR_IMPROPER_DATE);
    }

    protected LocalTime extractTime(String[] dateTime) throws DukeException {
        if (dateTimeFormat == null) {
            throw new DukeException(CommandMessage.ERROR_NO_DATETIME_EXPECTED);
        }
        List<DateTimeFormatter> timeFormatterList = dateTimeFormat.getTimeFormatterList();
        String timeString = dateTime[1].toUpperCase();
        for (DateTimeFormatter formatter: timeFormatterList) {
            try {
                return LocalTime.parse(timeString, formatter);
            } catch (DateTimeParseException exception) {
                // do nothing
            }
        }
        throw new DukeException(CommandMessage.ERROR_IMPROPER_TIME);
    }
}
