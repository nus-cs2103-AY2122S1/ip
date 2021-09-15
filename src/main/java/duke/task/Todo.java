package duke.task;

/**
 * A task class which falls under todo category.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo instance which is one of the task's type.
     *
     * @param tags The string array consisting all the tags.
     * @param description The task description.
     */
    public Todo(String[] tags, String description) {
        super(tags, description);
    }

    /**
     * Returns a string representation in the format to be written in tasks.txt file.
     *
     * @return The string representation in the format to be written in tasks.txt file.
     */
    @Override
    public String toDataString() {
        return String.format("TODO %s", super.toDataString());
    }

    /**
     * Returns a string representation of this task.
     *
     * @return The string representation of this task.
     */
    @Override
    public String toString() {
        String taskType = "[T]";
        String taskDescription = super.toString();

        return taskType + taskDescription;
    }
}
