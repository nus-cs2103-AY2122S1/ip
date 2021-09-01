package duke;

public class Event extends Task {

    private String date;

    /**
     * Returns an Event instance.
     * @param description
     * @param date
     */
    public Event(String description, String date) {
        this.description = description;
        this.date = date;
        this.done = false;
    }

    /**
     * Returns an Event instance.
     * @param description
     * @param date
     */
    public Event(String description, String date, boolean done) {
        this.description = description;
        this.date = date;
        this.done = done;
    }

    @Override
    public String toString() {
        String output = "[E]";
        if (this.done) {
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
        String done = (this.done ? "1" : "0");
        output += DIVIDER + done + DIVIDER + this.description + DIVIDER + this.date;
        return output;
    }
}
