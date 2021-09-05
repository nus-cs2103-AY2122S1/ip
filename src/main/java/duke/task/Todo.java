package duke.task;

/**
 * A class that extends Task, that represents a task that has no dates associated with it.
 */
public class Todo extends Task {
    public Todo(String taskDescription) {
        super(taskDescription);
    }

    /**
     * Returns a string that represents a serialized store format of the task that is specific to Duke.
     *
     * @return a string of serialized format.
     */
    @Override
    public String toDukeStoreFormat() {
        return String.format("T | %s", super.toDukeStoreFormat());
    }

    /**
     * Returns a string that shows the details of the task in a standardized format.
     *
     * @return a string of task details.
     */
    @Override
    public String toString() {
        return String.format("[T] %s", super.toString());
    }
}
