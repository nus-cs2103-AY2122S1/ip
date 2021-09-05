package duke;

public class Deadline extends DatedTask {

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
    public String toWriteString() {
        String output = Duke.COMMAND_DEADLINE;
        String isDone = (this.isDone ? "1" : "0");
        output += DIVIDER + isDone + DIVIDER + this.description + DIVIDER + this.date;
        return output;
    }
}
