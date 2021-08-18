package duke.tasks;

import duke.exceptions.BadInputFormatException;

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
    private Deadline(String description) throws BadInputFormatException {
        this(parse(description)[0], parse(description)[1]);
    }

    /**
     * duke.tasks.Deadline constructor.
     *
     * @param description the deadline's description
     * @param by the date to complete the task by
     */
    private Deadline(String description, String by) {
        super(description, by);
    }

    /**
     * Factory duke.tasks.Deadline method.
     *
     * @param description the user's input
     * @return a new duke.tasks.Deadline object
     */
    public static Deadline of(String description) throws BadInputFormatException {
        return new Deadline(description);
    }

    /**
     * Parses the description into tokens as string arrays.
     *
     * @param description the user's input
     * @return an array of tokens represented as strings; index 0 contains the description, index 1 contains the time
     * @throws BadInputFormatException if the input is not properly formatted
     */
    private static String[] parse(String description) throws BadInputFormatException {
        String[] tokens = description.split(" /by ");
        if (tokens.length < 2) {
            throw new BadInputFormatException();
        }
        return tokens;
    }

    @Override
    public String toString() {
        return String.format("[D]%s %s (by: %s)", isDone ? "[X]" : "[ ]", description, date);
    }
}
