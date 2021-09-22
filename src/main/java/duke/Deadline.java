package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class includes methods required for creating a deadline and
 * obtaining information relating to it.
 */
public class Deadline extends Task {
    private String by;
    private String type;
    private LocalDate completionDate;

    /**
     * Constructor for creating a deadline.
     *
     * @param information refers to details of deadline
     * @param by refers to when task should be completed
     * @param type refers to type of task
     */
    public Deadline(String information, String by, String type) {
        super(information);
        this.completionDate = LocalDate.parse(by);
        this.type = type;
        this.by = by;
        assert completionDate != null : "Deadline needs to have a date";
    }

    /**
     * Returns the type of task.
     * @return task type
     */
    public String getType() {
        return type;
    }

    /**
     * Returns the details about the deadline.
     *
     * @return deadline details
     */
    public String getDetails() {
        return by;
    }

    /**
     * Returns the deadline in a string format.
     *
     * @return deadline formatted as a string
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + completionDate
                .format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + ")";
    }
}
