package duke.task;

import java.util.List;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The Deadline class encapsulates information
 * and methods pertaining to Deadline tasks.
 *
 * @author Frederick Pek
 * @version CS2103 AY21/22 Sem 1 iP
 */
public class Deadline extends Task {
    private String by;
    private LocalDate date;

    /**
     * Creates and initalizes a new Deadline task with the parameters.
     *
     * @param description The description of the task.
     * @param by The string representing when the task is due.
     * @return A new Deadline object.
     */
    public Deadline(String description, String by) {
        super(description);
        this.type = "Deadline";

        try {
            this.date = LocalDate.parse(by);
            this.by = date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        } catch (DateTimeParseException ex) {
            this.date = null;
            this.by = by;
        }
    }

    /**
     * Returns the format a user should use to creating this task with Duke.
     *
     * @return Returns the format a user should use to creating this task with Duke.
     */
    public String getFormat() {
        return "deadline <description> /by <deadline>";
    }

    /**
     * Returns the list of parameters used to represent this task.
     *
     * @return Returns a list of parameters.
     */
    public List<String> getSaveParameters() {
        List<String> params = super.getSaveParameters();
        params.add(by);
        return params;
    }

    /**
     * The String representation of this Task object.
     *
     * @return Returns the String representation of this Task object.
     */
    public String toString() {
        return super.toString() + " (by: " + by + ")";
    }
}