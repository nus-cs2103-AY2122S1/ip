package duke.task;

public class Event extends DatedTask {
    protected static final String COMMAND_EVENT = "event";

    /**
     * Returns an Event instance.
     * @param description
     * @param date
     */
    public Event(String description, String date) {
        super(description, date);
    }

    /**
     * Returns an Event instance.
     * @param description
     * @param date
     */
    public Event(String description, String date, boolean isDone) {
        super(description, date, isDone);
    }

    @Override
    public String toString() {
        String output = "[E]";
        if (this.isDone) {
            output += "[X] ";
        } else {
            output += "[] ";
        }
        output += this.description;
        output += String.format(" (at: %s)", this.date);
        return output;
    }

    @Override
    /**
     * Returns proper format to write to txt file.
     */
    public String toWriteString() {
        String output = COMMAND_EVENT;
        output += super.toWriteString();
        return output;
    }
}
