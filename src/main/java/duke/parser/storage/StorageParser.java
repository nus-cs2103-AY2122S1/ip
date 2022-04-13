package duke.parser.storage;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.parser.UnableToParseException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

/**
 * This class encapsulates the storage parser.
 */
public class StorageParser {
    public static final UnableToParseException UNABLE_TO_PARSE_EXCEPTION =
            new UnableToParseException("storage file");
    /**
     * Parses the todo string from storage.
     *
     * @param todoSplitString The todo string from read from storage.
     * @return The todo object.
     * @throws UnableToParseException Thrown if the string read from storage is invalid.
     */
    public static Todo parseTodo(String[] todoSplitString) throws UnableToParseException {
        if (todoSplitString.length != 3) {
            throw UNABLE_TO_PARSE_EXCEPTION;
        }

        boolean isDone = parseStringToIsDone(todoSplitString[1]);
        String description = todoSplitString[2];
        Todo todo = new Todo(description);
        if (isDone) {
            todo.markAsDone();
        }
        return todo;
    }

    /**
     * Parsers the event string from storage.
     *
     * @param eventSplitString The event string from read from storage.
     * @return The event object.
     * @throws UnableToParseException Thrown if the string read from storage is invalid.
     */
    public static Event parseEvent(String[] eventSplitString) throws UnableToParseException {
        if (eventSplitString.length != 4) {
            throw UNABLE_TO_PARSE_EXCEPTION;
        }

        boolean isDone = parseStringToIsDone(eventSplitString[1]);
        String description = eventSplitString[2];
        String at = eventSplitString[3];
        Event event = new Event(description, at);
        if (isDone) {
            event.markAsDone();
        }
        return event;
    }

    /**
     * Parsers the deadline string from storage.
     *
     * @param deadlineSplitString The deadline string from read from storage.
     * @return The deadline object.
     * @throws UnableToParseException Thrown if the string read from storage is invalid.
     */
    public static Deadline parseDeadline(String[] deadlineSplitString) throws UnableToParseException {
        if (deadlineSplitString.length != 4) {
            throw UNABLE_TO_PARSE_EXCEPTION;
        }

        boolean isDone = parseStringToIsDone(deadlineSplitString[1]);
        String description = deadlineSplitString[2];
        LocalDate by;
        try {
            by = LocalDate.parse(deadlineSplitString[3]);
        } catch (DateTimeParseException exception) {
            throw UNABLE_TO_PARSE_EXCEPTION;
        }

        Deadline deadline = new Deadline(description, by);
        if (isDone) {
            deadline.markAsDone();
        }
        return deadline;
    }

    /**
     * Parses the isDone boolean to a string format to be saved in storage.
     * @param isDone The isDone boolean value.
     * @return Returns "1" if true. Else, returns "0";
     */
    public static String parseIsDoneToString(boolean isDone) {
        return isDone ? "1" : "0";
    }

    /**
     * Parses a 1 or 0 into true and false values respectively.
     *
     * @param s The string to be parsed.
     * @return The boolean that correspond to the String.
     * @throws UnableToParseException Thrown if the String to be parsed is invalid.
     */
    public static boolean parseStringToIsDone(String s) throws UnableToParseException {
        switch (s) {
        case "1":
            return true;
        case "0":
            return false;
        default:
            throw new UnableToParseException("isDone value: " + s);
        }
    }
}
