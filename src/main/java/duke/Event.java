package duke;

public class Event extends Task {

    private String date;

    /**
     * Returns an Event instance.
     * @param description
     * @param date
     */
    public Event(String description, String date) {
        this(description, date, false);
    }

    /**
     * Returns an Event instance.
     * @param description
     * @param date
     */
    public Event(String description, String date, boolean isDone) {
        this.description = description;
        this.date = date;
        this.isDone = isDone;
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

    /**
     * Returns proper format to write to txt file.
     */
    public String toWriteString() {
        String output = Duke.COMMAND_EVENT;
        String isDone = (this.isDone ? "1" : "0");
        output += DIVIDER + isDone + DIVIDER + this.description + DIVIDER + this.date;
        return output;
    }
}
