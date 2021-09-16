package meap.task;

public class Event extends Task {
    public static final String SYMBOL = "E";
    public static final String COMMAND_REGEX = "event \\w[\\w, ]+\\w \\/at \\w[\\w, ]*";
    public static final String COMMAND_SYNTAX = "event <task> /at <eventTime>";
    private String eventTime;


    public static Event of(String taskSummary, String eventTime) {
        return new Event(taskSummary, eventTime);
    }

    /**
     * Factory method of Event class.
     * Takes in a String, parses it and returns the Event instance it represented
     *
     * @param storageLine string representing task
     * @return Event instance which the string represented
     */
    public static Event parse(String storageLine) {
        //example line: "E | 0 | party | house"
        String[] args = storageLine.split(" \\| ");
        if (args.length != 4) {
            throw new IllegalArgumentException("storage line passed in doesnt have enough arguments");
        }
        Event loadedEvent = new Event(args[2], args[3]);
        boolean completed = args[1].equals("1");
        if (completed) {
            loadedEvent.markCompleted();
        }
        return loadedEvent;
    }

    public Event(String taskSummary, String eventTime) {
        super(taskSummary);
        this.eventTime = eventTime;
    }

    /**

    /**
     * Converts an Task instance to a string to be stored.
     *
     * @return line of text detailing task details.
     */
    public String toStorageFormat() {
        return String.format(
            "%s | %d | %s | %s",
            Event.SYMBOL, this.isCompleted() ? 1 : 0, this.getTaskSummary(), this.eventTime
        );
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Event) {
            Event event = (Event) obj;
            boolean isDescSame = event.getTaskSummary().equals(this.getTaskSummary());
            boolean isTimeSame = event.eventTime.equals(this.eventTime);
            return isDescSame && isTimeSame;
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format(
            "[%s][%s] %s (at: %s)",
            Event.SYMBOL,
            this.completeStatus(),
            this.getTaskSummary(),
            this.eventTime
        );
    }
}
