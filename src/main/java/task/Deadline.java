package task;

/**
 * The is the Deadline class that extends from Task.
 * Deadline task needs to be done before a specific time.
 *
 * @author  HU JIAJUN
 * @version %I%, %G%
 * @since   1.0
 */

public class Deadline extends Task {
    public static final String ID = "D";
    private final String byTime;

    /**
     * This is constructor method of Deadline.
     *
     * @param name   task name
     * @param byTime a specific time of task that needs to be done before it
     */
    public Deadline(String name, String byTime) {
        super(name);
        this.byTime = byTime;
    }

    /**
     * This is constructor method of Deadline.
     *
     * @param name   task name
     * @param byTime a specific time of task that needs to be done before it
     * @param isDone task status: done or not done
     */
    public Deadline(String name, String byTime, boolean isDone) {
        super(name, isDone);
        this.byTime = byTime;
    }

    /**
     * Get the specific time of task that needs to be done before it.
     *
     * @return the specific time of task that needs to be done before it
     */
    public String getByTime() {
        return byTime;
    }

    /**
     * Format Task to String array:
     * If task is done, [D, 0, Task1, DateTime]; else, [D, 1, Task1, DateTime]
     *
     * @return Task in String array
     */
    @Override
    public String[] formatTask() {
        String done;
        if (isDone()) {
            done = "0";
        } else {
            done = "1";
        }
        return new String[]{ID, done, super.getName(), getByTime()};
    }

    /**
     * Mark task status as done.
     *
     * @return task with done status
     */
    @Override
    public Deadline markAsDone() {
        return new Deadline(super.getName(), getByTime(), true);
    }

    /**
     * Print task with format:
     * If task is done, [D][X] Task1; else, [D][ ] Task1.
     */
    @Override
    public String toString() {
        return "[" + ID + "]" + super.toString() + " (by: " + byTime + ")";
    }
}
