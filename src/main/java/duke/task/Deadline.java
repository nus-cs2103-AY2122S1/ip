package duke.task;
import duke.task.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * The deadline class is a task class that has an additional parameter by.
 */
public class Deadline extends Task {

    protected LocalDateTime by;

    /**
     * Basic constructor.
     * @param description The description of the task
     * @param by The time the task should be completed by
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Overloaded constructor where the isDone field can be set.
     * @param description The description of the task.
     * @param by The time the task should be completed by.
     * @param isDone Whether the task has been completed.
     */
    public Deadline(String description, LocalDateTime by, boolean isDone) {
        super(description,
                isDone);
        this.by = by;
    }

    /**
     * Overridden toString method to print the object.
     * @return String representation of the object.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.formatDate() + ")";
    }


    /**
     * Date formatter that formats it to dd/MM/yyyy.
     * @return String of the formatted date.
     */
    public String formatDate() {

        if (by.getHour() == 0 && by.getMinute() == 0) {
            return by.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } else {
            return this.saveDate();
        }

    }

    /**
     * Prints the object in a save-friendly format.
     * @return String of the object in a save friendly format.
     */
    @Override
    public String saveString() {
        return String.format("D|%s|%s|%s",
                super.description,
                saveDate(),
                super.isDone ? "1" : "0");
    }

    /**
     * Prints the date in a save-friendly format.
     * @return String of the data in a save friendly format.
     */
    public String saveDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        return by.format(formatter);
    }
}
