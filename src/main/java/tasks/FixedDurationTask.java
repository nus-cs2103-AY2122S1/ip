package tasks;

/**
 * This is a FixedDurationTask class, which inherits from Task.
 */
public class FixedDurationTask extends Task {
    public static final String KEYWORD = "[Fixed]";
    public final int duration;

    /**
     * Constructor for FixedDurationTask.
     * @param taskName The description of the deadline task.
     * @param duration The duration of the task.
     */
    public FixedDurationTask(String taskName, String duration) {
        super(taskName);
        this.duration = Integer.parseInt(duration);
    }

    /**
     * Returns the duration of the task.
     * @return The string representation of duration.
     */
    public int getDuration() {
        return this.duration;
    }

    @Override
    public String toString() {
        return KEYWORD + " " + super.toString() + "\n\t(for: "
                + this.duration + " hour/s)";
    }
}
