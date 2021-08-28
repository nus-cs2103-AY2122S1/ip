package duke.task;

public class Event extends Task {
    public static final String SYMBOL = "E";
    public static final String COMMAND_REGEX = "event \\w[\\w, ]+\\w \\/at \\w[\\w, ]*";
    private String eventTime;


    public static Event of(String taskSummary, String eventTime) {
        return new Event(taskSummary, eventTime);
    }

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

    public static String syntax() {
        return "event command syntax: 'event <task> /at <eventTime>'";
    }

    public String toStorageFormat() {
        return String.format(
            "%s | %d | %s | %s",
            Event.SYMBOL, this.isCompleted() ? 1 : 0, this.getTaskSummary(), this.eventTime
        );
    }

    @Override
    public String toString() {
        return String.format(
            "[%s][%s] %s (at: %s)",
            Event.SYMBOL,
            this.isCompleted() ? "X" : "",
            this.getTaskSummary(),
            this.eventTime
        );
    }
}
