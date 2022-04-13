package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Encapsulates a deadline task.
 */
public class Deadline extends Task {

    private LocalDateTime by;

    /**
     * Constructor for deadline.
     *
     * @param description Details of deadline.
     * @param by Date of completion of deadline.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns string of deadline in the format to be saved.
     *
     * @return String representation of deadline to be saved.
     */
    @Override
    public String saveTaskFormat(){
        return "D" + super.saveTaskFormat() + String.format("|%s", by);
    }

    /**
     * Returns string of deadline in list.
     *
     * @return String representation of deadline.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy h:mm a");
        String formattedBy = by.format(formatter);
        return "[D]" + super.toString() + " (by: " + formattedBy + ")";
    }
}