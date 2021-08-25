package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    LocalDate dueDate;

    public Deadline(String desc, Boolean isDone, LocalDate time) {
        super(desc, isDone);
        this.dueDate = time;
    }

    /**
     * Provides the String representation of the task in the format
     * meant for writing to the file.
     *
     * @return The string representation of this deadline for the file to be saved to.
     */
    @Override
    public String saveText() {
        int isDone = this.isDone ? 1 : 0;
        return "D | " + isDone + " | " + desc + " | " + dueDate + "\n";
    }

    @Override
    public String toString() {
        return "[D]" + this.completionStatus() + desc + " (by: "
                + dueDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
