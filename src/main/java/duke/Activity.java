package duke;

public class Activity extends Task {
    protected int duration;

    /**
     * Class Constructor with the description and time of the task.
     *
     * @param description description of the task.
     * @param duration of the activity.
     */
    public Activity(String description, String duration) {
        super(description);
        this.duration = Integer.parseInt(duration);
    }

    /**
     * Returns the String representation of a Activity task.
     *
     * @return String representation of a Activity task.
     */
    @Override
    public String toString() {
        return "[A]" + super.toString() + " (needs "
                + duration + " hours)";
    }

    /**
     * Returns the String representation of a Activity task for storage purpose.
     *
     * @return String representation of a Activity task for storage purpose.
     */
    @Override
    public String toHistory() {
        return "A" + super.toHistory() + " | " + this.duration;
    }
}
