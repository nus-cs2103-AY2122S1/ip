package duke;

import java.time.LocalDateTime;
import java.time.LocalTime;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * Takes in the user's inputs and processes them to be understood by Duke to run commands.
 */
public abstract class Parser {
    public enum CommandType {
        EXIT,
        LIST,
        DONE,
        DELETE,
        FIND,
        TODO,
        DEADLINE,
        EVENT,
        UNKNOWN
    }

    /**
     * Takes in the user's String input and determines the type of Duke command it is.
     *
     * @param input The String input given by the user.
     * @return The type of Duke command represented by the String input.
     */
    public static CommandType decipherInput(String input) {
        if (input.equals("bye")) {
            return CommandType.EXIT;

        } else if (input.equals("list")) {
            return CommandType.LIST;

        } else if (input.startsWith("done")) {
            return CommandType.DONE;

        } else if (input.startsWith("delete")) {
            return CommandType.DELETE;

        } else if (input.startsWith("find")) {
            return CommandType.FIND;

        } else if (input.startsWith("todo")) {
            return CommandType.TODO;

        } else if (input.startsWith("deadline")) {
            return CommandType.DEADLINE;

        } else if (input.startsWith("event")) {
            return CommandType.EVENT;

        } else {
            return CommandType.UNKNOWN;
        }
    }

    /**
     * Takes in the string representation of a task and returns the task represented by it.
     *
     * @param taskString The string representation of a task.
     * @return The task represented by the string.
     */
    public static Task convertStringToTask(String taskString) {
        assert taskString.length() != 0 : "String representation of task is empty.";
        char type = taskString.charAt(1);
        boolean isDone = taskString.charAt(0) == 'X';
        String[] splitAtSlash = taskString.split("/");
        switch (type) {
        case 'T':
            return new ToDo(splitAtSlash[1], isDone);

        case 'D':
            String deadlineName = splitAtSlash[1];
            String deadlineDateAndTime = splitAtSlash[2];
            return new Deadline(deadlineName, LocalDateTime.parse(deadlineDateAndTime), isDone);

        default:
            String eventName = splitAtSlash[1];
            String eventDateAndTime = splitAtSlash[2];
            String eventEndTime = splitAtSlash[3];
            return new Event(eventName, LocalDateTime.parse(eventDateAndTime),
                    LocalTime.parse(eventEndTime), isDone);
        }
    }

    /**
     * Takes in a string representation of a date and time and parses it into a LocalDateTime object.
     *
     * @param dateAndTime The given date and time.
     * @return A LocalDateTime object.
     * @throws DukeException If the input date and time are incorrectly configured.
     */
    public static LocalDateTime formatDateTime(String dateAndTime) throws DukeException {
        try {
            String[] splitAtComma = dateAndTime.split(", ");
            String toFormat = splitAtComma[0];
            if (splitAtComma.length > 1) {
                toFormat += "T" + splitAtComma[1];
            } else {
                toFormat += "T" + "00:00";
            }
            return LocalDateTime.parse(toFormat);
        } catch (Exception e) {
            throw new DukeException("☹ OOPS!!! Please use the following format for date and time:\n"
                    + "yyyy-MM-dd, HH:mm");
        }
    }

    /**
     * Takes in the string of the done command entered by the user and returns the
     * task number of the task to be marked as done.
     *
     * @param input The done command entered by the user.
     * @return The task number to be marked as done.
     * @throws DukeException If the given done command has missing arguments.
     */
    public static int parseDoneCommand(String input) throws DukeException {
        assert input.length() != 0 : "Invalid Done command.";
        String[] splitBySpace = input.split(" ");
        if (splitBySpace.length > 1) {
            return Integer.parseInt(splitBySpace[1].trim());

        } else {
            throw new DukeException("☹ OOPS!!! Please state which task number "
                    + "you want to mark\n as done.");
        }
    }

    /**
     * Takes in the string of the delete command entered by the user and returns the
     * task number of the task to deleted.
     *
     * @param input The delete command entered by the user.
     * @return The task number to be deleted.
     * @throws DukeException If the given delete command has missing arguments.
     */
    public static int parseDeleteCommand(String input) throws DukeException {
        assert input.length() != 0 : "Invalid Delete command.";
        String[] splitBySpace = input.split(" ", 2);

        if (splitBySpace.length > 1 && splitBySpace[1].trim().length() > 0) {
            return Integer.parseInt(splitBySpace[1].trim());

        } else {
            throw new DukeException("☹ OOPS!!! Please state which task number "
                    + "you want to\n delete.");
        }
    }

    /**
     * Takes in the string of the find command entered by the user and returns the
     * substring to search for.
     *
     * @param input The find command entered by the user.
     * @return The substring to search for.
     * @throws DukeException If the given find command has missing arguments.
     */
    public static String parseFindCommand(String input) throws DukeException {
        assert input.length() != 0 : "Invalid Find command.";
        String[] splitBySpace = input.split(" ", 2);
        if (splitBySpace.length > 1 && splitBySpace[1].trim().length() > 0) {
            return splitBySpace[1].trim();

        } else {
            throw new DukeException("☹ OOPS!!! Please give a search query.");
        }
    }

    /**
     * Takes in the substring of the todo command entered by the user and returns a
     * Todo object representing the input.
     *
     * @param input The substring of the todo command entered by the user.
     * @return The Todo object.
     */
    public static ToDo parseTodoCommand(String input) {
        assert input.length() != 0 : "Invalid Todo command.";
        return new ToDo(input, false);
    }

    /**
     * Takes in the substring of the deadline command entered by the user and returns a
     * Deadline object representing the input.
     *
     * @param input The substring of the deadline command entered by the user.
     * @return The Deadline object.
     * @throws DukeException If the given deadline command has missing arguments.
     */
    public static Deadline parseDeadlineCommand(String input) throws DukeException {
        assert input.length() != 0 : "Invalid Deadline command.";
        String[] nameAndDeadline = input.split(" /by ");

        if (nameAndDeadline.length > 1
                && nameAndDeadline[1].trim().length() > 0) {
            LocalDateTime deadline = Parser
                    .formatDateTime(nameAndDeadline[1]);
            return new Deadline(nameAndDeadline[0], deadline,
                    false);

        } else {
            throw new DukeException("☹ OOPS!!! Please provide a date or "
                    + "time for the deadline.");
        }
    }

    /**
     * Takes in the substring of the event command entered by the user and returns a
     * Event object representing the input.
     *
     * @param input The substring of the event command entered by the user.
     * @return The Event object.
     * @throws DukeException If the given event command has missing arguments.
     */
    public static Event parseEventCommand(String input) throws DukeException {
        assert input.length() != 0 : "Invalid Event command.";
        String[] nameAndTime = input.split(" /at ");

        if (nameAndTime.length > 1
                && nameAndTime[1].trim().length() > 0) {
            String[] splitEndTime = nameAndTime[1].split(" - ");
            LocalDateTime eventTime = Parser
                    .formatDateTime(splitEndTime[0]);

            if (splitEndTime.length > 1
                    && splitEndTime[1].trim().length() > 0) {
                LocalTime endTime = LocalTime.parse(splitEndTime[1]);
                return new Event(nameAndTime[0],
                        eventTime, endTime, false);

            } else {
                throw new DukeException("☹ OOPS!!! Please provide an end "
                        + "time for the event.");
            }

        } else {
            throw new DukeException("☹ OOPS!!! Please provide a date or "
                    + "time for the event.");
        }
    }
}
