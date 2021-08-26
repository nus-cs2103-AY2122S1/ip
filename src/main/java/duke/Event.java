package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    private LocalDate taskDate;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");

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
