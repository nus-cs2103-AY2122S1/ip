package duke;

public class Deadline extends Task {

    private String date;

    /**
     * Returns a Deadline instance.
     * @param description
     * @param date
     */
    public Deadline(String description, String date) {
        this(description, date, false);
    }

    /**
     * Returns a Deadline instance.
     * @param description
     * @param date
     */
    public Deadline(String description, String date, boolean isDone) {
        this.description = description;
        this.date = date;
        this.isDone = isDone;
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
