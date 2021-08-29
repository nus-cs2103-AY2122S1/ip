package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


/**
 * Class for Event, a child class of Task.
 * @author Liew Jian Hong
 */
public class Event extends Task {
    /**
     * The date the event.
     */
    protected LocalDate date;

    /**
     * Constructor for an Event task.
     *
     * @param desc String array consisting of parsed description.
     */
    public Event(String[] desc) {

        super(desc[1], Boolean.parseBoolean(desc[3]));
        this.date = LocalDate.parse(desc[2]);

    }

    /**
     * Converts the task into a string to be written into storage.
     *
     * @return String to be written into storage
     */
    @Override
    public String toWrite() {
        return "event--" + this.isDone + "--" + this.desc + "--" + this.date.toString() + "\n";
    }

    /**
     * Returns a string representation of the event.
     *
     * @return Return the type, completion status and description of the event.
     */
    @Override
    public String toString() {
        String dateString = this.date.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
        return "[E]" + super.toString() + " (at: " + dateString + ")\n";
    }
}
