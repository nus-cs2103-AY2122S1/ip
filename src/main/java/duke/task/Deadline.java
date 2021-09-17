package duke.task;

public class Deadline extends DatedTask {
    protected static final String COMMAND_DEADLINE = "deadline";

    /**
     * Returns a Deadline instance.
     * @param description
     * @param date
     */
    public Deadline(String description, String date) {
        super(description, date);
    }

    /**
     * Returns a Deadline instance.
     * @param description
     * @param date
     */
    public Deadline(String description, String date, boolean isDone) {
        super(description, date, isDone);
    }

    @Override
    public String toString() {
        String output = "[D]";
        if (this.isDone) {
            output += "[X] ";
        } else {
            output += "[] ";
        }
        output += this.description;
        output += String.format(" (by: %s)", this.date);
        return output;
    }

    /**
     * Returns proper format to write to txt file.
     */
    @Override
    public String toWriteString() {
        String output = COMMAND_DEADLINE;
        output += super.toWriteString();
        return output;
    }
}
