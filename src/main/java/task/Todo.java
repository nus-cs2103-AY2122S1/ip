package task;

/**
 * The is the Todo class that extends from Task.
 * Todo task does not have any date/time attached to it.
 *
 * @author  HU JIAJUN
 * @version %I%, %G%
 * @since   1.0
 */

public class Todo extends Task {
    public static final String ID = "T";

    /**
     * This is constructor method of Todo.
     *
     * @param name task name
     */
    public Todo(String name) {
        super(name);
    }

    /**
     * This is constructor method of Todo.
     *
     * @param name   task name
     * @param isDone task status: done or not done
     */
    public Todo(String name, boolean isDone) {
        super(name, isDone);
    }

    /**
     * Format Task to String array:
     * If task is done, [T, 0, Task1]; else, [T, 1, Task1]
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
        return new String[]{ID, done, super.getName()};
    }

    /**
     * Mark task status as done.
     *
     * @return task with done status
     */
    @Override
    public Todo markAsDone() {
        return new Todo(super.getName(), true);
    }

    /**
     * Print task with format:
     * If task is done, [T][X] Task1; else, [T][ ] Task1.
     */
    @Override
    public String toString() {
        return "[" + ID + "]" + super.toString();
    }
}
