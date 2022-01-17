package tipsy;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate taskDate;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");

    /**
     * Class constructor.
     *
     * @param taskName The name and description of task.
     * @param taskDate The date and time of the event in the task.
     * @param isDone Whether the task is done or not.
     */
    public Event(String taskName, LocalDate taskDate, boolean isDone) {
        super(taskName, TaskType.EVENT, isDone);
        this.taskDate = taskDate;
    }

    public Event(String taskName, LocalDate taskDate) {
        this(taskName, taskDate, false);
    }

    @Override
    public String toSaveFormat() {
        return String.format("E, %d, %s, %s", isDone() ? 1 : 0, getTaskName(), taskDate);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), taskDate.format(formatter));
    }
}
