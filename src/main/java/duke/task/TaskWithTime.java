package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class TaskWithTime extends Task {
    public final DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("E, dd MMM yyyy HHmm");
    protected LocalDateTime dateTime;
    /**
     * Class Constructor
     *
     * @param description the description of the task
     */
    public TaskWithTime(String description, LocalDateTime dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
