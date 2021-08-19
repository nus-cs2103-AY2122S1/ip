package util;

import exception.DukeExtractCommandException;
import exception.DukeTaskNumberOutOfBoundsException;
import exception.DukeUnknownException;
import task.EventDateTime;
import task.Operation;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * The is the CommandUtils class that extracts contents from command.
 *
 * @author  HU JIAJUN
 * @version %I%, %G%
 * @since   1.0
 */

public class CommandUtils {

    /**
     * Extract operation from command.
     *
     * @return operation from command if exists, else throw exception
     * @throws DukeExtractCommandException if operation is empty or cannot be extracted properly
     * @throws DukeUnknownException if operation is unknown
     */
    public static Operation extractOperation(String command) throws DukeExtractCommandException, DukeUnknownException {
        String[] contents = command.split(" ");
        if (contents.length == 0) {
            throw new DukeExtractCommandException("☹ OOPS!!! The operation cannot be extracted properly.");
        }
        String operation = contents[0];
        if (operation.equals("")) {
            throw new DukeExtractCommandException("☹ OOPS!!! The operation cannot be empty.");
        } else {
            if (operation.equals(Operation.TODO.getValue())) {
                return Operation.TODO;
            } else if (operation.equals(Operation.DEADLINE.getValue())) {
                return Operation.DEADLINE;
            } else if (operation.equals(Operation.EVENT.getValue())) {
                return Operation.EVENT;
            } else if (operation.equals(Operation.LIST.getValue())) {
                return Operation.LIST;
            } else if (operation.equals(Operation.DONE.getValue())) {
                return Operation.DONE;
            } else if (operation.equals(Operation.DELETE.getValue())) {
                return Operation.DELETE;
            } else if (operation.equals(Operation.CLEAR.getValue())) {
                return Operation.CLEAR;
            } else if (operation.equals(Operation.FIND.getValue())) {
                return Operation.FIND;
            } else if (operation.equals(Operation.BYE.getValue())) {
                return Operation.BYE;
            } else {
                throw new DukeUnknownException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
    }

    /**
     * Extract task number from command.
     *
     * @return task number from command if it exists and is a positive integer,
     *         else throw exception
     * @throws DukeExtractCommandException if task number is empty
     * @throws NumberFormatException if task number is not a integer
     * @throws DukeTaskNumberOutOfBoundsException if task number is not a positive integer
     */
    public static int extractTaskNumber(String command) throws DukeExtractCommandException, NumberFormatException, DukeTaskNumberOutOfBoundsException {
        String[] contents = command.split(" ", 2);
        if (contents.length != 2) {
            throw new DukeExtractCommandException("☹ OOPS!!! The task number cannot be extracted properly.");
        }
        int number = 0;
        try {
            number = Integer.parseInt(contents[1]);
        } catch (Exception e) {
            throw new NumberFormatException("☹ OOPS!!! The task number is not an integer.");
        }
        if (number < 1) {
            throw new DukeTaskNumberOutOfBoundsException("☹ OOPS!!! The task number is not a positive integer.");
        }
        return number;
    }

    /**
     * Extract task description from command.
     *
     * @return task description from command if it exists and can be extracted properly,
     *         else throw exception
     * @throws DukeExtractCommandException if task description is empty or cannot be extracted properly
     */
    public static String extractTaskDescription(String command) throws DukeExtractCommandException {
        String[] contents = command.split(" ", 2);
        String operation = contents[0];
        if (contents.length != 2) {
            throw new DukeExtractCommandException("☹ OOPS!!! The description of a " + operation + " cannot be extracted properly.");
        }
        String description = contents[1];
        if (description.equals("")) {
            throw new DukeExtractCommandException("☹ OOPS!!! The description of a " + operation + " cannot be empty.");
        }
        return description;
    }

    /**
     * Extract task details that contains task name and task (at/by) time from task description.
     *
     * @param  description extracted from task command
     * @param  regex " /at " or " /by "
     * @return task details from description if they exist and can be extracted properly,
     *         else throw exception
     * @throws DukeExtractCommandException if task details are empty or cannot be extracted properly
     */
    public static String[] extractTaskDetails(String description, String regex) throws DukeExtractCommandException {
        String[] details = description.split(regex, 2);
        if (details.length != 2) {
            throw new DukeExtractCommandException("☹ OOPS!!! The task details cannot be extracted properly.");
        }
        for (String detail: details) {
            if (detail.equals("")) {
                throw new DukeExtractCommandException("☹ OOPS!!! The task details cannot be empty.");
            }
        }
        return details;
    }

    /**
     * Extract event date, start time and end time.
     *
     * @param  dateTime extracted from description
     * @param  regex " "
     * @return date, start time and end time from datetime if they exist and can be extracted properly,
     *         else throw exception
     * @throws DukeExtractCommandException if dateTime are empty or cannot be extracted properly
     */
    public static EventDateTime extractEventDatetime(String dateTime, String regex) throws DukeExtractCommandException {
        String[] dateAndTimes = dateTime.split(regex, 3);
        if (dateAndTimes.length != 3) {
            throw new DukeExtractCommandException("☹ OOPS!!! The event date and time cannot be extracted properly.");
        }
        String atDate = dateAndTimes[0];
        String startTime = dateAndTimes[1];
        String endTime = dateAndTimes[2];
        try {
            return new EventDateTime(DateTimeUtils.parseDate(atDate), DateTimeUtils.parseTime(startTime), DateTimeUtils.parseTime(endTime));
        } catch (DateTimeParseException e) {
            throw new DukeExtractCommandException("☹ OOPS!!! The event date and time cannot be extracted properly.");
        }
    }

    /**
     * Extract deadline ate and time.
     *
     * @param  dateTime extracted from description
     * @return dateTime format if it can be extracted properly, else throw exception
     * @throws DukeExtractCommandException if dateTime cannot be extracted properly
     */
    public static LocalDateTime extractDeadlineDateTime(String dateTime) throws DukeExtractCommandException {
        try {
            return DateTimeUtils.parseDateTime(dateTime);
        } catch (DateTimeParseException e) {
            throw new DukeExtractCommandException("☹ OOPS!!! The deadline date and time cannot be extracted properly.");
        }
    }

    /**
     * Extract keyword from command.
     *
     * @return keyword from command if it can be extracted properly,
     *         else throw exception
     * @throws DukeExtractCommandException if keyword cannot be extracted properly
     */
    public static String extractKeyword(String command) throws DukeExtractCommandException {
        String[] contents = command.split(" ", 2);
        if (contents.length != 2) {
            throw new DukeExtractCommandException("☹ OOPS!!! The keyword cannot be extracted properly.");
        }
        return contents[1];
    }
}
