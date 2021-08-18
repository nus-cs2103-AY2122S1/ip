package task;

/**
 * The is the Event class that extends from Task.
 * Event task starts at a specific time and ends at a specific time.
 *
 * @author  HU JIAJUN
 * @version %I%, %G%
 * @since   1.0
 */

public class Event extends Task {
    public static final String ID = "E";
    private final String atTime;

    /**
     * This is constructor method of Event.
     *
     * @param name   task name
     * @param atTime a specific time of task start and end
     */
    public Event(String name, String atTime) {
        super(name);
        this.atTime = atTime;
    }

    /**
     * This is constructor method of Event.
     *
     * @param name   task name
     * @param atTime a specific time of task start and end
     * @param isDone task status: done or not done
     */
    public Event(String name, String atTime, boolean isDone) {
        super(name, isDone);
        this.atTime = atTime;
    }

    /**
     * Get the specific time of task start and end.
     *
     * @return the specific time of task start and end
     */
    public String getAtTime() {
        return atTime;
    }

    /**
     * Format Task to String array:
     * If task is done, [E, 0, Task1, DateTime]; else, [E, 1, Task1, DateTime]
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
        return new String[]{ID, done, super.getName(), getAtTime()};
    }

    /**
     * Mark task status as done.
     *
     * @return task with done status
     */
    @Override
    public Event markAsDone() {
        return new Event(super.getName(), getAtTime(), true);
    }

    /**
     * Print task with format:
     * If task is done, [E][X] Task1; else, [E][ ] Task1.
     */
    @Override
    public String toString() {
        return "[" + ID + "]" + super.toString() + " (at: " + atTime + ")";
    }
}
