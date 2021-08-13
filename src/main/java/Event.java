public class Event extends Task {
    /** The time when the event is happening. */
    private final String at;

    /**
     * Event constructor.
     *
     * @param description the event's description
     */
    private Event(String description) {
        this(parse(description)[0], parse(description)[1]);
    }

    /**
     * Event constructor.
     *
     * @param description the event's description
     * @param at the event's time
     */
    private Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Factory Event method.
     *
     * @param description the user's input
     * @return a new Event object
     */
    public static Event of(String description) {
        Event newEvent = new Event(description);
        feedback(newEvent.toString());
        return newEvent;
    }

    /**
     * Parses the description into tokens as string arrays.
     *
     * @param description the user's input
     * @return an array of tokens represented as strings; index 0 contains the description, index 1 contains the time
     */
    private static String[] parse(String description) {
        String[] tokens = description.split(" /at ");
        return tokens;
    }

    @Override
    public String toString() {
        return String.format("[E]%s %s (at: %s)", isDone ? "[X]" : "[ ]", description, at);
    }
}
