package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline-type Task is a task consisting of its details and deadline in day.
 */
public class Deadline extends Task {
    private static final DateTimeFormatter inputDateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter outputDateFormat = DateTimeFormatter.ofPattern("MMM d yyyy");

    protected LocalDate date;


    /**
     * Constructs a instance of Deadline that consist of its details and deadline in day.
     *
     * @param taskDetails Description of the task
     * @param date day in dd/MM/yyyy
     */
    public Deadline(String taskDetails, String date) {
        super(taskDetails);
        this.date = LocalDate.parse(date, inputDateFormat);
    }

    /**
     * Return the string representation of Deadline details with day and time, prefixed with [D]
     *
     * @return the string representation of Deadline details
     */
    @Override
    public String toString() {
        String outputDate = this.date.format(outputDateFormat);
        return "[D]" + super.toString() + " (by: " + outputDate + ")";
    }

    /**
     * Returns the string representation for storing in text file.
     *
     * @return the string representation for storing in text file
     */
    @Override
    public String toEncodedString() {
        int completeBinary = 0;
        if (this.isComplete) {
            completeBinary = 1;
        }
        return "D" + " | " + completeBinary + " | " + this.taskDetails + " | "
                + this.date.format(inputDateFormat);
    }
}
