package task;

/**
 * A class that encapsulates a Todo Task stored by Duke.
 */
public class TodoTask extends Task {

    /**
     * Constructor for the TodoTask class.
     *
     * @param task The task to be stored within this TodoTask object.
     */
    public TodoTask(String task) {
        super(task);
    }

    /**
     * Constructor for the TodoTask class.
     *
     * @param task The task to be stored within this TodoTask object.
     * @param tags The tags to be stored within this TodoTask object.
     */
    public TodoTask(String task, String tags) {
        super(task, tags);
    }

    /**
     * Constructor for the TodoTask class.
     * This constructor is invoked when reading from the local data, in order to show the correct Task state.
     *
     * @param task The task to be stored within this TodoTask object.
     * @param isDone The state of the TodoTask object.
     */
    public TodoTask(String task, String tags, boolean isDone) {
        super(task, tags, isDone);
    }

    /**
     * Returns the type of this Task object.
     *
     * @return A String corresponding to the type of this Task object.
     */
    @Override
    public String getTaskType() {
        return super.getTaskType() + " (Todo)";
    }

    /**
     * Returns the String representation of the TodoTask object, showing the state and the task.
     *
     * @return A String enumerating this TodoTask object.
     */
    @Override
    public String getTaskState() {
        return "[T]" + super.getTaskState() + "\n" + this.tags.toString().trim();
    }

    /**
     * Returns the storage format of this TodoTask object.
     *
     * @return A String representation of the TodoTask object, formatted for storage inside local hard disk.
     */
    @Override
    public String convertToStorageFormat() {
        return "T,"
                + (isDone ? "1," : "0,")
                + task
                + ","
                + tags.toString()
                + ",";
    }
}
