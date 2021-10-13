package duke;

/**
 * Represents a Deadline Task
 */
public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = TimeHandler.parse(by);
    }

    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = TimeHandler.parse(by);
    }

    @Override
    public String convertToSavableString() {
        return "D" + super.convertToSavableString() + " | " + by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}