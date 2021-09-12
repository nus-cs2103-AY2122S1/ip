package duke.task;

/**
 * A task with a fixed duration.
 */
public class DurationTask extends Task {
    private final int duration;

    /**
     * Constructs a task with a fixed duration.
     *
     * @param name     of the task
     * @param duration in minutes
     */
    public DurationTask(String name, int duration) {
        super(name);
        this.duration = duration;
    }

    /**
     * Constructs a task with a fixed duration with a boolean of whether the task is
     * completed.
     *
     * @param name        of the task
     * @param duration    in minutes
     * @param isCompleted whether the task is completed
     */
    public DurationTask(String name, int duration, boolean isCompleted) {
        super(name, isCompleted);
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (duration: " + this.duration + " minutes)";
    }

    public int getDuration() {
        return this.duration;
    }

}
