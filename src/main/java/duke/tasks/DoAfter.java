package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class that encapsulates a DoAfter.
 * A DoAfter is a Task with a do after datetime.
 */
public class DoAfter extends Task {

    /** A LocalDateTime object representing the do after datetime. */
    private LocalDateTime doAfterDateTime;

    /**
     * A constructor for a DoAfter.
     *
     * @param description The String description of the DoAfter.
     * @param doAfterDateTime The LocalDateTime representing the do after datetime.
     */
    public DoAfter(String description, LocalDateTime doAfterDateTime) {
        super(description);
        this.doAfterDateTime = doAfterDateTime;
    }

    /**
     * An alternate constructor for a DoAfter that may be completed.
     *
     * @param description The String description of the DoAfter.
     * @param doAfterDateTime The LocalDateTime representing the do after datetime.
     * @param isComplete A Boolean representing if the DoAfter is completed.
     */
    public DoAfter(String description, LocalDateTime doAfterDateTime, Boolean isComplete) {
        super(description, isComplete);
        this.doAfterDateTime = doAfterDateTime;
    }

    /**
     * Returns an easily parsable, String file representation of a
     * DoAfter for use in persistent storage.
     *
     * @return An easily parsable String representing the DoAfter.
     */
    @Override
    public String getFileRepr() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return "DA" + super.getFileRepr() + " | " + this.doAfterDateTime.format(formatter);
    }

    /**
     * Returns a String representation of a DoAfter for use in
     * the Duke UI.
     *
     * @return A user-friendly, readable String representing the DoAfter.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return "[DA]["
                + ((this.isCompleted()) ? "X] " : " ] ")
                + this.getDescription()
                + " (after: "
                + this.doAfterDateTime.format(formatter)
                + ")";
    }
}
