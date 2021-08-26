package bubbles.tasks;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

/**
 * A child class of Task, representing the tasks that need to be
 * done before a specific date/time.
 */
public class Deadline extends Task {
    private LocalDate dueDate;

    private Deadline(String description, boolean completed, String dueDate) {
        super(description, completed);
        this.dueDate = Task.formatDate(dueDate);
    }

    /**
     * Act as a public constructor of a Deadline Object.
     *
     * @param input The description of the Deadline, including the due date
     * @param completed Whether the Deadline is done/completed
     * @return The created Deadline Object
     */
    public static Deadline addDeadline(String input, boolean completed) {
        String[] arr = input.split(" /by ");

        Deadline item = new Deadline(arr[0], completed, arr[1]);

        return item;
    }

    /**
     * Format the Deadline Object to store in the hard disk File.
     *
     * @return String representing the Deadline Object (different from the String representation
     *          of the Deadline Task)
     */
    @Override
    public String format() {
        String format = "D | ";

        if (this.isDone) {
            format += "1 | ";
        } else {
            format += "0 | ";
        }

        format += this.description;
        format += " | by ";
        format += this.dueDate;

        return format;
    }

    /**
     * Return the String representation of the Deadline.
     *
     * @return The String representation of the Deadline.
     */
    @Override
    public String toString() {
        String date = this.dueDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String due = "(by: " + date + ")";

        String res = "[D] [" + this.getStatus() + "] " + this.description + " " + due;

        return res;
    }
}