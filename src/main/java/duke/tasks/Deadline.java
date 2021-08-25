package duke.tasks;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Deadline class used to represent a task that has an end date.
 * Contains method that
 * (i) overrides the Parent toString method to display the task type,
 * as well as status and description.
 */
public class Deadline extends Task {
    private LocalDate date;
    private LocalTime time;

    public Deadline(String description, LocalDate date, LocalTime time) {
        super(description);
        this.date = date;
        this.time = time;
    }

    public Deadline(String done, String description, LocalDate date, LocalTime time) {
        super(description);
        this.date = date;
        this.time = time;
        if ((done.equals("X"))) {
            this.setIsDone(true);
        } else {
            this.setIsDone(false);
        }
    }

    public String formatString() {
        return "[D]" + super.toString() + " (by: " + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " "
                + this.time.format(DateTimeFormatter.ofPattern("h:mma")) + ")";
    }

    /**
     * Overriding toString method to display the relevant information
     *
     * @return String type object that includes the task type, parent
     * toString method(), and due date.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.date + " " + this.time + ")";
    }
}
