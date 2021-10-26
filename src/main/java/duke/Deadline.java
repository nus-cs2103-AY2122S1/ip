package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Event class is a task that takes in description, deadline and time
 */
public class Deadline extends Task {
    protected LocalDate deadline;
    protected String logo = "[D]";
    protected String time;

    public Deadline(String description, LocalDate deadline, String time) {
        super(description);
        this.deadline = deadline;
        this.time = time;
    }

    /**
     * Returns logo corresponding to type
     *
     * @return the logo [E]
     */
    public String logo() {
        return logo;
    }

    private String formatDate() {
        return deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    public String saveWithFormat () {
        return logo() + isDone + "_" + super.description + "_" + deadline + "_" + time;
    }

    @Override
    public String toString() {
        return "[D]" + super.getStatusIcon() + " " + super.description + " (by: " + formatDate() + " " + time + ")";
    }
}
