package duke.task;

/**
 * This is the Todo class that extends from Task.
 * Todo task does not have any date/time attached to it.
 */
public class Todo extends Task {
    public static final String ID = "T";

    /**
     * Constructs a Todo object.
     *
     * @param name Task name.
     */
    public Todo(String name) {
        super(name);
    }

    /**
     * Constructs a Todo object.
     *
     * @param name Task name.
     * @param isDone Task status: done or not done.
     */
    public Todo(String name, boolean isDone) {
        super(name, isDone);
    }

    /**
     * Formats Task to String array.
     * If task is done, [T, 0, Task1]; else, [T, 1, Task1].
     *
     * @return Task in String array.
     */
    @Override
    public String[] formatTask() {
        String done;
        if (isDone()) {
            done = "0";
        } else {
            done = "1";
        }
        return new String[] {ID, done, super.getName()};
    }

    /**
     * Returns task with done status.
     *
     * @return Task with done status.
     */
    @Override
    public Todo markAsDone() {
        return new Todo(super.getName(), true);
    }

    /**
     * Prints task.
     * If task is done, [T][X] Task1; else, [T][ ] Task1.
     */
    @Override
    public String toString() {
        return "[" + ID + "]" + super.toString();
    }
}
