package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    protected LocalDate deadline;

    public Deadline(String description, LocalDate deadline) {
        super(description);
        this.deadline = deadline;
    }

    public Deadline(String description, LocalDate deadline, boolean isDone) {
        super(description, isDone);
        this.deadline = deadline;
    }

    @Override
    public boolean hasDueDate(LocalDate dueDate) {
        return dueDate.isEqual(this.deadline);
    }

    @Override
    public String getFormattedData() {
        return super.getFormattedData() + "|" + this.deadline;
    }

    @Override
    public String getTaskIdentifier() {
        return "D";
    }

    @Override
    public String toString() {
        return "[D][" + getStatusIcon() + "] " + this.description + " (by: " + this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

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
            } else return this.deadline.equals(otherDeadline.deadline);
        }
    }
}
