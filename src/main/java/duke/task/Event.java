package duke.task;

import duke.exception.BadInputFormatException;

/**
 * Represents an duke.tasks.Event object.
 */
public class Event extends DatedTask {
    /**
     * duke.tasks.Event constructor.
     *
     * @param description the event's description
     * @throws BadInputFormatException if the description is badly formatted
     */
    private Event(String description) throws BadInputFormatException {
        this(parse(description)[0], parse(description)[1]);
    }

    /**
     * duke.tasks.Event constructor.
     *
     * @param description the event's description
     * @param at the event's time
     */
    private Event(String description, String at) {
        super(description, at);
    }

    /**
     * Factory duke.tasks.Event method.
     *
     * @param description the user's input
     * @return a new duke.tasks.Event object
     * @throws BadInputFormatException if the input is badly formatted
     */
    public static Event of(String description) throws BadInputFormatException {
        return new Event(description);
    }

    /**
     * Parses the description into tokens as string arrays.
     *
     * @param description the user's input
     * @return an array of tokens represented as strings; index 0 contains the description, index 1 contains the time
     * @throws BadInputFormatException if the input is not properly formatted
     */
    private static String[] parse(String description) throws BadInputFormatException {
        String[] tokens = description.split(" /at ");
        if (tokens.length < 2) {
            throw new BadInputFormatException();
        }
        return tokens;
    }

    @Override
    public String toString() {
        return String.format("[E]%s %s (at: %s)", isDone ? "[X]" : "[ ]", description, date);
    }
}
