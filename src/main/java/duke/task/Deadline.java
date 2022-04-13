package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that needs to be done before a specific date.
 * E.g. submit assignment by Nov-9-2021.
 */
public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Class constructor.
     *
     * @param description Description of the Deadline.
     * @param by Date of the Deadline.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
    }

    /**
     * Returns the date of the deadline formatted as [MMM-dd-yyyy]
     *
     * @return The formatted date
     */
    public String getDate() {
        DateTimeFormatter dateFormatObj = DateTimeFormatter.ofPattern("MMM-dd-yyyy");
        String formattedDate = by.format(dateFormatObj);
        assert (formattedDate.length() > 0);
        return formattedDate;
    }

    /**
     * Returns formatted string to write to the duke.txt file.
     *
     * @return String to write to duke.txt
     */
    @Override
    public String toWriteFormat() {
        String done = this.isDone ? "1" : "0";
        return String.format("D | %s | %s | %s", done, this.getDescription(), this.by);
    }

    /**
     * Returns the string representation of the Deadline.
     *
     * @return String representation of Deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.getDate() + ")";
    }

    /**
     * Checks if the current task object is the same as a given task object.
     *
     * @param obj The given task object.
     * @return True if equals, False if not equals.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        Deadline other = (Deadline) obj;
        if (!this.description.equals(other.getDescription())
                || !this.getDate().equals(other.getDate())) {
            return false;
        }
        return true;
    }

    /** Returns a hash of the current object.
     *
     * @return The hash.
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + (this.description != null ? this.description.hashCode() : 0);
        hash = 53 * hash + (this.by != null ? this.by.hashCode() : 0);
        return hash;
    }
}
