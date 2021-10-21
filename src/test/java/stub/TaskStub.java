package stub;

import java.time.LocalDateTime;

import duke.task.Task;

/**
 * A dummy class for Task.
 */
public class TaskStub extends Task {
    private final LocalDateTime dateTime;

    /**
     * Creates a new task object with the description and type of task.
     *
     * @param description The description of the task
     * @param dateTime    The date and time of this task
     */
    public TaskStub(String description, LocalDateTime dateTime) {
        super(description, "S");
        this.dateTime = dateTime;
    }

    @Override
    public LocalDateTime getDateTime() {
        return dateTime;
    }
}
