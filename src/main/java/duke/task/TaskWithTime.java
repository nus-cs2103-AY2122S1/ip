package duke.task;

import java.time.format.DateTimeFormatter;

public abstract class TaskWithTime extends Task {
    public final DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("E, dd MMM yyyy HHmm");
    /**
     * Class Constructor
     *
     * @param description the description of the task
     */
    public TaskWithTime(String description) {
        super(description);
    }
}
