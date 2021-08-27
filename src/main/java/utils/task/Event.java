package utils.task;

/**
 * The Deadline class encapsulates a task that start at a specific time and ends at a specific time.
 */
public class Event extends Task {

    private String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String getTaskFileString(String delimiter, String done, String notDone) {
        return "E" + delimiter + (this.isDone ? done : notDone) + delimiter + this.description + delimiter + this.at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
