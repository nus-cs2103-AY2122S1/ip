package bern.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A class to represents a Task of type "deadline".
 */
public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Constructor for Deadline.
     *
     * @param description The description of the Task.
     * @param by When should the task be done by.
     */
    public Deadline(String description, String by) {
        super(description);
        try {
            this.by = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            this.by = LocalDate.parse(by, DateTimeFormatter.ofPattern("MMM dd yyyy"));
        }
    }

    /**
     * A method to return the String representation of the class.
     *
     * @return The String representation of the class.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd YYYY")) + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Deadline)) {
            return false;
        } else {
            Deadline compared = (Deadline) obj;
            if (compared.description.equals(this.description) && compared.by.equals(this.by)) {
                return true;
            } else {
                return false;
            }
        }
    }
}
