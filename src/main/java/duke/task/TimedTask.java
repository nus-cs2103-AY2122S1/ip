package duke.task;

public abstract class TimedTask extends Task {
    public TimedTask(String description) {
        super(description);
    }

    public TimedTask(String description, boolean isDone) {
        super(description, isDone);
    }

    public abstract void changeDate(String newDate);
}
