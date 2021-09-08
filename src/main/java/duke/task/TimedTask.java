package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class TimedTask extends Task {
    protected LocalDateTime date;
    private static final DateTimeFormatter DATABASE_DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    public TimedTask(String description) {
        super(description);
    }

    public TimedTask(String description, boolean isDone) {
        super(description, isDone);
    }

    public abstract void changeDate(String newDate);
}
