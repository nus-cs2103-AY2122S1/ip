package duke.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

import duke.exception.DukeExtractCommandException;
import duke.exception.DukeTaskNumberOutOfBoundsException;
import duke.exception.DukeUnknownException;
import duke.task.EventDateTime;
import duke.task.Operation;

/**
 * This is the CommandUtils class that extracts contents from command.
 */
public class CommandUtils {

    /**
     * Returns operation from command.
     *
     * @return Operation from command if exists, else throw exception.
     * @throws DukeExtractCommandException If operation is empty or cannot be extracted properly.
     * @throws DukeUnknownException If operation is unknown.
     */
    public static Operation extractOperation(String command)
            throws DukeExtractCommandException, DukeUnknownException {
        String[] contents = command.split(" ");
        if (contents.length == 0) {
            throw new DukeExtractCommandException(
                    "☹ OOPS!!! The operation cannot be extracted properly.");
        }
        String operation = contents[0];
        if (operation.equals("")) {
            throw new DukeExtractCommandException(
                    "☹ OOPS!!! The operation cannot be empty.");
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
            } else if (operation.equals(Operation.COMING.getValue())) {
                return Operation.COMING;
            } else if (operation.equals(Operation.BYE.getValue())) {
                return Operation.BYE;
            } else {
                throw new DukeUnknownException(
                        "☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
    }

    /**
     * Returns task number from command.
     *
     * @return Task number from command if it exists and is a positive integer,
     *         else throw exception.
     * @throws DukeExtractCommandException If task number is empty.
     * @throws NumberFormatException If task number is not a integer.
     * @throws DukeTaskNumberOutOfBoundsException If task number is not a positive integer.
     */
    public static int extractTaskNumber(String command)
            throws DukeExtractCommandException, NumberFormatException,
        DukeTaskNumberOutOfBoundsException {
        String[] contents = command.split(" ", 2);
        if (contents.length != 2) {
            throw new DukeExtractCommandException(
                    "☹ OOPS!!! The task number cannot be extracted properly.");
        }
        int number = 0;
        try {
            number = Integer.parseInt(contents[1]);
        } catch (Exception e) {
            throw new NumberFormatException("☹ OOPS!!! The task number is not an integer.");
        }
        if (number < 1) {
            throw new DukeTaskNumberOutOfBoundsException(
                    "☹ OOPS!!! The task number is not a positive integer.");
        }
        return number;
    }

    /**
     * Returns task description from command.
     *
     * @return Task description from command if it exists and can be extracted properly,
     *         else throw exception.
     * @throws DukeExtractCommandException If task description is empty
     *                                     or cannot be extracted properly.
     */
    public static String extractTaskDescription(String command) throws DukeExtractCommandException {
        String[] contents = command.split(" ", 2);
        String operation = contents[0];
        if (contents.length != 2) {
            throw new DukeExtractCommandException(
                    "☹ OOPS!!! The description of a "
                            + operation + " cannot be extracted properly.");
        }
        String description = contents[1];
        if (description.equals("")) {
            throw new DukeExtractCommandException(
                    "☹ OOPS!!! The description of a "
                            + operation + " cannot be empty.");
        }
        return description;
    }

    /**
     * Returns task details that contains task name and task (at/by) time from task description.
     *
     * @param description Extracted from task command.
     * @param regex " /at " or " /by ".
     * @return Task details from description if they exist and can be extracted properly,
     *         else throw exception.
     * @throws DukeExtractCommandException If task details are empty
     *                                     or cannot be extracted properly.
     */
    public static String[] extractTaskDetails(String description, String regex)
            throws DukeExtractCommandException {
        String[] details = description.split(regex, 2);
        if (details.length != 2) {
            throw new DukeExtractCommandException(
                    "☹ OOPS!!! The task details cannot be extracted properly.");
        }
        if (Arrays.stream(details).filter(detail -> detail.equals(""))
                .toArray(String[]::new).length > 0) {
            throw new DukeExtractCommandException(
                    "☹ OOPS!!! The task details cannot be empty.");
        }
        return details;
    }

    /**
     * Returns event date, start time and end time.
     *
     * @param dateTime Extracted from description.
     * @param regex " ".
     * @return Date, start time and end time from datetime if they exist
     *         and can be extracted properly, else throw exception.
     * @throws DukeExtractCommandException If dateTime are empty or cannot be extracted properly.
     */
    public static EventDateTime extractEventDatetime(String dateTime, String regex)
            throws DukeExtractCommandException {
        String[] dateAndTimes = dateTime.split(regex, 3);
        if (dateAndTimes.length != 3) {
            throw new DukeExtractCommandException(
                    "☹ OOPS!!! The event date and time cannot be extracted properly.");
        }
        String atDate = dateAndTimes[0];
        String startTime = dateAndTimes[1];
        String endTime = dateAndTimes[2];
        try {
            return new EventDateTime(DateTimeUtils.parseDate(atDate),
                DateTimeUtils.parseTime(startTime), DateTimeUtils.parseTime(endTime));
        } catch (DateTimeParseException e) {
            throw new DukeExtractCommandException(
                    "☹ OOPS!!! The event date and time cannot be extracted properly.");
        }
    }

    /**
     * Returns deadline ate and time.
     *
     * @param dateTime Extracted from description.
     * @return DateTime format if it can be extracted properly, else throw exception.
     * @throws DukeExtractCommandException If dateTime cannot be extracted properly.
     */
    public static LocalDateTime extractDeadlineDateTime(String dateTime)
        throws DukeExtractCommandException {
        try {
            return DateTimeUtils.parseDateTime(dateTime);
        } catch (DateTimeParseException e) {
            throw new DukeExtractCommandException(
                    "☹ OOPS!!! The deadline date and time cannot be extracted properly.");
        }
    }

    /**
     * Returns keyword from command.
     *
     * @return Keyword from command if it can be extracted properly,
     *         else throw exception.
     * @throws DukeExtractCommandException If keyword cannot be extracted properly.
     */
    public static String extractKeyword(String command) throws DukeExtractCommandException {
        String[] contents = command.split(" ", 2);
        if (contents.length != 2) {
            throw new DukeExtractCommandException(
                    "☹ OOPS!!! The keyword cannot be extracted properly.");
        }
        return contents[1];
    }
}
