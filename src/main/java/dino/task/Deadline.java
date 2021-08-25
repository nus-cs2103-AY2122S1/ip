package dino.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Tasks that need to be done before a specific date/time
 * e.g., submit report by 11/10/2019 5pm
 * It is a subclass of Task
 */
public class Deadline extends Task {

    private LocalDate by;

    /**
     * Constructs a Deadline object
     *
     * @param description the description for the deadline task
     * @param by the time by which the deadline task needs to be done
     */
    public Deadline(String description, LocalDate by){
        super(description);
        this.by = by;
    }

    /**
     * Displays the deadline task in the format of "D | Status | Description | Time",
     * The time is displayed in the format of "MMM dd yyyy"
     *
     * @return the deadline task in the required format
     */
    @Override
    public String toString() {
        return "D" + super.toString() + " | "
                + this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
}
