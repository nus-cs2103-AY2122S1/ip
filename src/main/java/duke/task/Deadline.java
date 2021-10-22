package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * A task that stores the deadline.
 */
public class Deadline extends Task {
    /**
     * The date of the deadline.
     */
    private LocalDate date;

    /**
     * The time of the deadline.
     */
    private LocalTime time;

    /**
     * A constructor used to initialize the deadline.
     *
     * @param description the description of the deadline.
     * @param date the date of the deadline
     * @param time the time of the deadline
     */
    public Deadline(String description, LocalDate date, LocalTime time) {
        super(description);
        this.date = date;
        this.time = time;
    }

    /**
     * A constructor used to initialize the deadline through file input.
     *
     * @param description the description of the event.
     * @param isCompleted the state of the event.
     * @param date the date of the deadline
     * @param time the time of the deadline
     */
    public Deadline(String description, boolean isCompleted, LocalDate date, LocalTime time) {
        super(description, isCompleted);
        this.date = date;
        this.time = time;
    }

    /**
     * Updates the deadline with the new deadline provided.
     *
     * @param updatedDeadline the deadline object which contains the updated information of deadline.
     */
    public void updateDeadline(Deadline updatedDeadline) {
        this.description = updatedDeadline.description;
        this.date = updatedDeadline.date;
        this.time = updatedDeadline.time;
    }

    /**
     * Returns the string representation of deadline.
     *
     * @return the string representation of deadline.
     */
    @Override
    public String toString() {
        String str = "[D]" + super.toString() + " (by: " + this.date.format(DateTimeFormatter.ofPattern("d/M/yyyy"));
        str += this.time != null ? " " + this.time + ")" : ")";
        return str;
    }

    /**
     * Returns the string representation of deadline for file input/output.
     *
     * @return the string representation of deadline for file input/output.
     */
    @Override
    public String fileFormat() {
        String displayCompletion = this.isCompleted ? "1" : "0";
        String str = String.format("%s | %s | %s | %s", "D", displayCompletion, this.description,
                this.date.format(DateTimeFormatter.ofPattern("d/M/yyyy")));
        str += this.time != null ? " | " + this.time : "";
        return str;
    }
}
