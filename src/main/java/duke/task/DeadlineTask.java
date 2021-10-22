package duke.task;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DeadlineTask extends Task {

    private LocalDate deadline;

    /**
     * Constructor for a DeadlineTask object.
     * @param name name of the task.
     * @param isDone whether or not task is done.
     * @param deadline deadline for the task.
     */
    public DeadlineTask(String name, boolean isDone, String deadline) throws DateTimeException {
        super(name, isDone);
        this.deadline = LocalDate.parse(deadline);
    }

    public DeadlineTask(String name, String deadline) throws DateTimeException {
        super(name);
        this.deadline = LocalDate.parse(deadline);
    }

    @Override
    public String formatForFile() {
        return "D" + super.formatForFile() + SAVE_DATA_MARKER + this.deadline + "\n";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + ")";
    }
}
