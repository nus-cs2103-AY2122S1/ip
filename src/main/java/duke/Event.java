package duke;

public class Event extends DatedTask {

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
