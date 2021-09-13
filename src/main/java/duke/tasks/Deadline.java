package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class that encaspulates a Deadline.
 * A Deadline is a Task with a due datetime.
 */
public class Deadline extends Task {

    /** A LocalDateTime object representing the due datetime. */
    private LocalDateTime dueDate;

    /**
     * A constructor for a Deadline
     *
     * @param description The String description of the Deadline
     * @param dueDate The LocalDateTime representing the due datetime
     */
    public Deadline(String description, LocalDateTime dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    /**
     * An alternate constructor for a Deadline that may be completed.
     *
     * @param description The String description of the Deadline
     * @param dueDate The LocalDateTime representing the due datetime
     * @param isComplete A Boolean representing if the Deadline is completed
     */
    public Deadline(String description, LocalDateTime dueDate, Boolean isComplete) {
        super(description, isComplete);
        this.dueDate = dueDate;
    }

    /**
     * Returns an easily parsable, String file representation of a
     * Deadline for use in persistent storage.
     *
     * @return An easily parsable String representing the Deadline
     */
    @Override
    public String getFileRepr() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return "D" + super.getFileRepr() + " | " + this.dueDate.format(format);
    }

    /**
     * Returns a String representation of a Deadline for use in
     * the Duke UI.
     *
     * @return A user-friendly, readable String representing the Deadline
     */
    @Override
    public String toString() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

        return "[D]["
            + ((this.isCompleted()) ? "X] " : " ] ")
            + this.getDescription()
            + " (by: "
            + this.dueDate.format(format)
            + ")";
    }
}
