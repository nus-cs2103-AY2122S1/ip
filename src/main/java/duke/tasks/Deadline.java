package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate deadline;

    /**
     * Constructs a Deadline Task with given description, deadline date & time and uncompleted flag by default.
     *
     * @param description Description of the deadline
     * @param deadline Date & time of the due date of this Deadline Task
     */
    public Deadline(String description, LocalDate deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Constructs a Deadline Task with given description, deadline date & time and completion flag.
     *
     * @param description Description of the deadline
     * @param deadline Date & time of the due date of this Deadline Task
     * @param isDone Completed flag for this Deadline
     */
    public Deadline(String description, LocalDate deadline, boolean isDone) {
        super(description, isDone);
        this.deadline = deadline;
    }

    /**
     * Returns true if the Task has a due date that is due on the given date.
     *
     * @param dueDate The date to check if this task is on the same deadline/due date
     * @return Returns true if this task is due the same date as the given one
     */
    @Override
    public boolean isDue(LocalDate dueDate) {
        return dueDate.isEqual(this.deadline);
    }


    /**
     * Returns a formatted version with delimiters of this task for saving to file.
     *
     * @return A formatted String representing the data stored in the task
     */
    @Override
    public String getFormattedData() {
        return super.getFormattedData() + "|" + this.deadline;
    }

    /**
     * Returns a letter identifying the Task as a Todo.
     *
     * @return A character identifying the Task
     */
    @Override
    public String getTaskIdentifier() {
        return "D";
    }

    /**
     * Returns a string representation of the Todo Task.
     *
     * @return String representing the Todo Task
     */
    @Override
    public String toString() {
        return "[D][" + getStatusIcon() + "] "
                + this.description + " (by: "
                + this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + ")";
    }

    /**
     * Does a deep comparison of this object to another object.
     *
     * @param otherObj The other object to be compared to
     * @return Returns true iff the two objects are of same type and same value in every field
     */
    @Override
    public boolean equals(Object otherObj) {
        if (!(otherObj instanceof Deadline)) {
            return false;
        } else {
            final Deadline otherDeadline = (Deadline) otherObj;

            if (this.isDone != otherDeadline.isDone) {
                return false;
            } else if (!this.description.equals(otherDeadline.description)) {
                return false;
            } else {
                return this.deadline.equals(otherDeadline.deadline);
            }
        }
    }
}
