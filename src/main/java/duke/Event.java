package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Event is a subclass of task
 * with a date.
 *
 * @author Samuel Lau
 */
public class Event extends Task {
    protected String atPart;
    protected LocalDate date;
    protected String afterDate;

    /**
     * Constructor for Event class.
     *
     * @param description The description of the task.
     * @param atPart The date of the event.
     */
    public Event(String description, String atPart) {
        super(description);
        this.atPart = atPart;
        String[] arr = atPart.split(" ", 2);
        this.afterDate = arr.length == 2 ? arr[1] : "";
        this.date = LocalDate.parse(arr[0]);
    }

    /**
     * Returns the string in the written format
     * to be saved in the text file.
     *
     * @return String to be saved.
     */
    @Override
    public String toWrite() {
        int marked = this.isDone ? 1 : 0;
        return String.format("E|%d|%s|%s\n", marked, this.description, this.atPart);
    }

    /**
     * Returns the string representation of the event object.
     *
     * @return String representation.
     */
    @Override
    public String toString() {
        return String.format("[E]%s (at: %s %s)", super.toString(),
                this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")), this.afterDate);
    }
}