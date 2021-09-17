package tipsy;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate taskDate;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    /**
     * Class constructor.
     *
     * @param taskName The name and description of task.
     * @param taskDate The date and time of the deadline of the task.
     * @param isDone Whether the task is done or not.
     */
    public Deadline(String taskName, LocalDate taskDate, boolean isDone) {
        super(taskName, TaskType.DEADLINE, isDone);
        this.taskDate = taskDate;
    }

    public Deadline(String taskName, LocalDate taskDate) {
        this(taskName, taskDate, false);
    }

    @Override
    public String toSaveFormat() {
        return String.format("D, %d, %s, %s", isDone() ? 1 : 0, getTaskName(), taskDate);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), taskDate.format(formatter));
    }
}
