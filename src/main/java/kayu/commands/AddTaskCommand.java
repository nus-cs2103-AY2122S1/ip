package kayu.commands;

import static kayu.commands.CommandMessage.ERROR_EMPTY_PARAMS;
import static kayu.commands.CommandMessage.ERROR_IMPROPER_DATE;
import static kayu.commands.CommandMessage.ERROR_IMPROPER_FORMATTING;
import static kayu.commands.CommandMessage.ERROR_IMPROPER_TIME;

import kayu.exception.DukeException;
import kayu.parser.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

/**
 * AddTaskCommand class.
 * 
 * This class is an instance of {@link kayu.commands.Command} and adds on methods that 
 * are used by {@link kayu.commands.Command}s that requires some additional parsing.
 * Some such parsing requirements are extracting the String description or {@link java.time.LocalDate}.
 */
public abstract class AddTaskCommand extends Command {

    private final DateTimeFormat dateTimeFormat;

    public AddTaskCommand(CommandType commandType, String commandParams, DateTimeFormat dateTimeFormat) {
        super(commandType, commandParams);
        this.dateTimeFormat = dateTimeFormat;
    }
    
    protected String[] splitUserParams(String userParams, String commandName, String splitKey) 
            throws DukeException {
        
        try {
            String[] splitOnKey = userParams.split(" /" + splitKey + ' ', 2);
            String[] dateTime = splitOnKey[1].split(" ", 2);
            return new String[] {splitOnKey[0], dateTime[0], dateTime[1]};
            
        } catch (ArrayIndexOutOfBoundsException exception) {
            throw new DukeException(String.format(
                    ERROR_IMPROPER_FORMATTING, 
                    commandName, 
                    splitKey));
        }
    }

    protected String extractDesc(String[] paramArray, String commandName) throws DukeException {
        assert (paramArray.length >= 1);
        
        String desc = paramArray[0].trim();
        if (desc.isBlank()) {
            throw new DukeException(String.format(ERROR_EMPTY_PARAMS, commandName));
        }
        return desc;
    }

    protected LocalDate extractDate(String[] paramArray) throws DukeException {
        assert (paramArray.length == 3);
        
        String dateString = paramArray[1].trim();
        List<DateTimeFormatter> dateFormatterList = dateTimeFormat.getDateFormatterList();
        
        for (DateTimeFormatter formatter: dateFormatterList) {
            try {
                return LocalDate.parse(dateString, formatter);
            } catch (DateTimeParseException exception) {
                // do nothing
            }
        }
        throw new DukeException(ERROR_IMPROPER_DATE);
    }

    protected LocalTime extractTime(String[] paramArray) throws DukeException {
        assert (paramArray.length == 3);
        
        String timeString = paramArray[2].trim().toUpperCase();
        List<DateTimeFormatter> timeFormatterList = dateTimeFormat.getTimeFormatterList();
        
        for (DateTimeFormatter formatter: timeFormatterList) {
            try {
                return LocalTime.parse(timeString, formatter);
            } catch (DateTimeParseException exception) {
                // do nothing
            }
        }
        throw new DukeException(ERROR_IMPROPER_TIME);
    }
}
