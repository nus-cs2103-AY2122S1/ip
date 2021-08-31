package duke;

/**
 * Represents a task with a time frame.
 */
public class Event extends Task {
    private String duration;

    public Event(String desc, String duration) {
        super(desc.trim());
        this.duration = duration;
    }

    public Event(String desc, String duration, boolean isDone) {
        super(desc.trim(), isDone);
        this.duration = duration.trim();
    }

    /**
     * Returns the task as a String formatted to be saved.
     *
     * @return The formatted String.
     */
    @Override
    public String saveTask() {
        return "E|" + this.isDone() + "|" + getDesc() + "|" + duration + "\n";
    }

    @Override
    public String toString() {
        return "[E]" + this.statusIcon() + this.getDesc() + " (at: " + this.duration + ")";
    }
}
