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
        UPDATE,
        DELETE,
        FIND,
        TODO,
        DEADLINE,
        EVENT,
        UNKNOWN
    }
    static final int MINIMUM_LENGTH_IF_CONTAINS_BOTH = 9;
    static final int MINIMUM_LENGTH_IF_CONTAINS_ONE = 4;

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

        } else if (input.startsWith("update")) {
            return CommandType.UPDATE;

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
            boolean hasInputAfterComma = splitAtComma.length > 1;

            if (hasInputAfterComma) {
                toFormat += "T" + splitAtComma[1];
            } else {
                toFormat += "T" + "00:00";
            }
            return LocalDateTime.parse(toFormat);
        } catch (Exception e) {
            throw new DukeException("Hey!!! Use the following format for date and time:\n"
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
        boolean hasInputAfterSpace = splitBySpace.length > 1
                && splitBySpace[1].trim().length() > 0;

        if (hasInputAfterSpace) {
            return Integer.parseInt(splitBySpace[1].trim());

        } else {
            throw new DukeException("Hey!!! Please give a task number "
                    + "you want me to mark as done.");
        }
    }

    private static boolean checkIfInteger(String string) {
        try {
            Integer.parseInt(string);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean findArguments(String string) {
        boolean hasDescription = string.contains("/d");
        boolean hasTime = string.contains("/t");
        int actualLength = string.length();
        if (hasDescription && hasTime) {
            return actualLength >= MINIMUM_LENGTH_IF_CONTAINS_BOTH;
        } else {
            return hasDescription && actualLength >= MINIMUM_LENGTH_IF_CONTAINS_ONE
                    || hasTime && actualLength >= MINIMUM_LENGTH_IF_CONTAINS_ONE;
        }
    }

    private static String[] extractArguments(String string) {
        String[] descriptionAndTime = new String[2];
        int descriptionIndex = string.indexOf("/d ");
        int timeIndex = string.indexOf("/t ");

        if (descriptionIndex < 0) {
            descriptionAndTime[1] = string.substring(timeIndex + 3);
        } else if (timeIndex < 0) {
            descriptionAndTime[0] = string.substring(descriptionIndex + 3);
        } else if (descriptionIndex < timeIndex) {
            String[] splitAtT = string.substring(descriptionIndex + 3).split("/t ");
            descriptionAndTime[0] = splitAtT[0].trim();
            descriptionAndTime[1] = splitAtT[1].trim();
        } else {
            String[] splitAtD = string.substring(timeIndex + 3).split("/d ");
            descriptionAndTime[0] = splitAtD[1].trim();
            descriptionAndTime[1] = splitAtD[0].trim();
        }

        return descriptionAndTime;
    }

    /**
     * Takes in the string of the update command entered by the user and extracts the
     * arguments passed into the update command.
     *
     * @param input The update command entered by the user.
     * @return The extracted arguments of the update command.
     * @throws DukeException If the given update command is missing arguments.
     */
    public static String[] parseUpdateCommand(String input) throws DukeException {
        assert input.length() != 0 : "Invalid Update command.";
        String[] splitBySpace = input.split(" ", 3);
        boolean hasTaskNumber = splitBySpace.length > 1
                && checkIfInteger(splitBySpace[1].trim());
        boolean hasArguments = splitBySpace.length > 2
                && findArguments(splitBySpace[2].trim());
        boolean isValidUpdateCommand = hasTaskNumber && hasArguments;

        if (isValidUpdateCommand) {
            String[] numberDescriptionAndTime = new String[3];
            numberDescriptionAndTime[0] = splitBySpace[1].trim();
            String[] commandArguments = extractArguments(splitBySpace[2].trim());
            numberDescriptionAndTime[1] = commandArguments[0];
            numberDescriptionAndTime[2] = commandArguments[1];
            return numberDescriptionAndTime;

        } else if (!hasTaskNumber) {
            throw new DukeException("Hey!!! Please state which task number "
                    + "you want me to update.");
        } else {
            throw new DukeException("Hey!!! Please provide a description or "
                    + "time to me update.");
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
        boolean hasInputAfterSpace = splitBySpace.length > 1
                && splitBySpace[1].trim().length() > 0;

        if (hasInputAfterSpace) {
            return Integer.parseInt(splitBySpace[1].trim());

        } else {
            throw new DukeException("Hey!!! Please state which task number "
                    + "you want me to delete.");
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
        boolean hasInputAfterSpace = splitBySpace.length > 1
                && splitBySpace[1].trim().length() > 0;

        if (hasInputAfterSpace) {
            return splitBySpace[1].trim();

        } else {
            throw new DukeException("Hey!!! Give me something to "
                    + "actually search for.");
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
        boolean hasInputAfterBy = nameAndDeadline.length > 1
                && nameAndDeadline[1].trim().length() > 0;

        if (hasInputAfterBy) {
            LocalDateTime deadline = Parser
                    .formatDateTime(nameAndDeadline[1]);
            return new Deadline(nameAndDeadline[0], deadline,
                    false);

        } else {
            throw new DukeException("Hey!!! Please provide a date or "
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
        boolean hasInputAfterAt = nameAndTime.length > 1
                && nameAndTime[1].trim().length() > 0;

        if (hasInputAfterAt) {
            String[] splitEndTime = nameAndTime[1].split(" - ");
            LocalDateTime eventTime = Parser
                    .formatDateTime(splitEndTime[0]);
            boolean hasInputAfterDash = splitEndTime.length > 1
                    && splitEndTime[1].trim().length() > 0;

            if (hasInputAfterDash) {
                LocalTime endTime = LocalTime.parse(splitEndTime[1]);
                return new Event(nameAndTime[0],
                        eventTime, endTime, false);

            } else {
                throw new DukeException("Hey!!! Please provide an end "
                        + "time for the event.");
            }

        } else {
            throw new DukeException("Hey!!! Please provide a date or "
                    + "time for the event.");
        }
    }
}
