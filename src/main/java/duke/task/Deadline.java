package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * The deadline class is a task class that has an additional parameter deadlineDate.
 */
public class Deadline extends Task {

    protected LocalDateTime deadlineDate;

    /**
     * Basic constructor.
     *
     * @param description The description of the task
     * @param deadlineDate The time the task should be completed deadlineDate
     */
    public Deadline(String description, LocalDateTime deadlineDate) {
        super(description);
        this.deadlineDate = deadlineDate;
    }

    /**
     * Overloaded constructor where the isDone field can be set.
     *
     * @param description The description of the task.
     * @param deadlineDate          The time the task should be completed deadlineDate.
     * @param isDone      Whether the task has been completed.
     */
    public Deadline(String description, LocalDateTime deadlineDate, boolean isDone) {
        super(description, isDone);
        this.deadlineDate = deadlineDate;
    }

    /**
     * Overridden toString method to print the object.
     *
     * @return String representation of the object.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (deadlineDate: " + this.formatDate() + ")";
    }


    /**
     * Date formatter that formats it to dd/MM/yyyy.
     *
     * @return String of the formatted date.
     */
    public String formatDate() {

        if (deadlineDate.getHour() == 0 && deadlineDate.getMinute() == 0) {
            return deadlineDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } else {
            return this.saveDate();
        }

    }

    /**
     * Prints the object in a save-friendly format.
     *
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
     *
     * @return String of the data in a save friendly format.
     */
    public String saveDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        return deadlineDate.format(formatter);
    }

    public LocalDateTime getDate() {
        return this.deadlineDate;
    }
}
