package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private final LocalDateTime dateTime;

    public Deadline(String taskName, LocalDateTime dateTime) {
        super(taskName);
        this.dateTime = dateTime;
    }

    private Deadline(Deadline oldDeadline) {
        super(oldDeadline);
        this.dateTime = oldDeadline.dateTime;
    }
    
    public static Deadline createTask(String name, boolean isCompleted, LocalDateTime dateTime) {
        Deadline d = new Deadline(name, dateTime);
        if (isCompleted) {
            return new Deadline(d);
        } else {
            return d;
        }
    }

    @Override
    public Deadline markAsCompleted() {
        return new Deadline(this);
    }

    @Override
    public String toString() {
        return "D: " + super.toString() + " before: " + this.dateTime.format(DateTimeFormatter.ofPattern(
                Task.dateTimeFormat));
    }
}
