package duke.task;

import duke.date.Date;
import duke.exception.BadInputFormatException;
import duke.exception.InvalidDateException;

/**
 * Represents a duke.tasks.Deadline object.
 */
public class Deadline extends DatedTask {
    /**
     * duke.tasks.Deadline constructor.
     *
     * @param description the deadline's description
     * @throws BadInputFormatException if the deadline is badly formatted
     */
    private Deadline(String description) throws BadInputFormatException, InvalidDateException {
        this(parseToDescription(description), parseToDate(description));
    }

    /**
     * duke.tasks.Deadline constructor.
     *
     * @param description the deadline's description
     * @param by the date to complete the task by
     */
    private Deadline(String description, Date by) {
        super(description, by);
    }

    /**
     * Factory duke.tasks.Deadline method.
     *
     * @param description the user's input
     * @return a new duke.tasks.Deadline object
     */
    public static Deadline of(String description) throws BadInputFormatException, InvalidDateException {
        return new Deadline(description);
    }

    private static Date parseToDate(String description) throws BadInputFormatException, InvalidDateException {
        String[] tokens = description.split(" /by ");
        if (tokens.length < 2) {
            throw new BadInputFormatException();
        }
        return Date.of(tokens[1]);
    }

    private static String parseToDescription(String description) throws BadInputFormatException {
        String[] tokens = description.split(" /by ");
        if (tokens.length < 2) {
            throw new BadInputFormatException();
        }
        return tokens[0];
    }

    @Override
    public String toString() {
        return String.format("[D]%s %s (by: %s)", isDone ? "[X]" : "[ ]", description, date);
    }
}
