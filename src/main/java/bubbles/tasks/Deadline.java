package bubbles.tasks;

import bubbles.exceptions.EmptyTaskException;
import bubbles.exceptions.InvalidFormatException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A child class of Task, representing the tasks that need to be
 * done before a specific date/time.
 */
public class Deadline extends Task {
    private LocalDate dueDate;

    private Deadline(String description, boolean isDone, String dueDate) throws InvalidFormatException {
        super(description, isDone);

        try {
            this.dueDate = Task.formatDate(dueDate);
        } catch (DateTimeParseException e) {
            throw new InvalidFormatException("The date you've entered should be in the format of yyyy-mm-dd.");
        }

    }

    /**
     * Acts as a public constructor of a Deadline Object.
     *
     * @param input The description of the Deadline, including the due date.
     * @param isDone Whether the Deadline is done/completed.
     * @return The created Deadline Object.
     */
    public static Deadline addDeadline(String input, boolean isDone) throws EmptyTaskException, InvalidFormatException {
        if (input.equals("")) {
            throw new EmptyTaskException("deadline");
        }

        String[] arr = input.split(" /by ");

        Deadline item;

        try {
            item = new Deadline(arr[0], isDone, arr[1]);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidFormatException("When adding a deadline to the task list, the date field cannot be empty.");
        }

        return item;
    }

    /**
     * Formats the Deadline Object to store in the hard disk File.
     *
     * @return String representing the Deadline Object (different from the String representation
     *          of the Deadline Task).
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
     * Gets the due date of this deadline and returns that to the user.
     *
     * @return The due date of the deadline, eg. 2021-09-09
     */
    @Override
    public LocalDate getDate() {
        return this.dueDate;
    }

    /**
     * Returns the String representation of the Deadline.
     *
     * @return The String representation of the Deadline.
     */
    @Override
    public String toString() {
        String date = this.dueDate.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
        String due = "(by: " + date + ")";
        String res = "[D] [" + this.getStatus() + "] " + this.description + " " + due;

        return res;
    }
}
