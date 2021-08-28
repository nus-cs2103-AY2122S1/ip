package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A Event-type Task consisting of event details, day and time.
 */
public class Event extends Task {
    private static final DateTimeFormatter inputDateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter outputDateFormat = DateTimeFormatter.ofPattern("MMM d yyyy");
    /**
     * The day and time in LocalDate.
     */
    protected LocalDate at;


    /**
     * Constructs a instance of Event that consist of event details, day and time
     *
     * @param taskDetails Description of the task
     * @param at day and time in string
     */
    public Event(String taskDetails, String at) {
        super(taskDetails);
        LocalDate byDate = LocalDate.parse(at, inputDateFormat);
        this.at = byDate;
    }

    /**
     * Return the string representation of Event details with day and time, prefixed with [E].
     *
     * @return the string representation of Event details
     */
    public String toString() {
        String outputDate = this.at.format(outputDateFormat);
        return "[E]" + super.toString() + " (at: " + outputDate + ")";
    }


    /**
     * Returns the string representation for storing in text file.
     *
     * @return the string representation for storing in text file
     */
    @Override
    public String toStringSave() {
        int completeBinary = 0;
        if (this.isComplete) {
            completeBinary = 1;
        }
        return "E" + " | " + completeBinary + " | " + this.taskDetails + " | " + this.at.format(inputDateFormat);
    }

}
