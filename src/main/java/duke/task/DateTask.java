package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exception.DukeException;

/**
 * Represents tasks that consists of a date.
 *
 * @author botr99
 */
public abstract class DateTask extends Task {
    private LocalDate date;
    private String dateString;

    /**
     * Constructs a task with the corresponding description,
     * whether it is done or not, and a date that is parsed
     * from the date string.
     *
     * @param description Description of the task.
     * @param dateString String to be parsed into a date.
     * @param isDone True if task is done; false otherwise.
     * @throws DukeException If date string cannot be parsed into a date.
     */
    public DateTask(String description, String dateString, boolean isDone) throws DukeException {
        super(description, isDone);
        assignDate(dateString);
        this.dateString = dateString;
    }

    private void assignDate(String dateString) throws DukeException {
        try {
            date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } catch (DateTimeParseException e) {
            throw new DukeException("Oops!!! Date should be in this format: dd/MM/yyyy");
        }
        assert date != null;
    }

    /**
     * Returns the date string passed in to the constructor.
     *
     * @return The date string that is used to parse it into a date.
     */
    public String getDateString() {
        return dateString;
    }

    public LocalDate getDate() {
        return date;
    }

    /**
     * Returns a string representation of the date of a task
     * that is formatted to a "d MMM yyyy" pattern.
     *
     * @return A string in the "d MMM yyy" format.
     */
    public String formatDate() {
        return date.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
    }

    /**
     * Checks if a date task is equal to another date task.
     *
     * @param obj The other date task to be compared with.
     * @return True if both tasks share the same description, done status
     *         and date string; false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof DateTask) || !super.equals(obj)) {
            return false;
        }
        DateTask other = (DateTask) obj;
        return dateString.equals(other.getDateString());
    }

}
