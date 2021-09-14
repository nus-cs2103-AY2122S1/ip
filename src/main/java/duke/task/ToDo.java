package duke.task;

/**
 * Represents a Todo type task.
 */
public class ToDo extends Task {


    private ToDo(String description) {
        super(description);
    }

    /**
     * Returns a ToDo object that is subclass of Task
     *
     * @param isDone      Boolean for if the task is done.
     * @param description String for the description of the Task
     * @return A Task that is a ToDo object.
     */
    public static Task of(boolean isDone, String description) {
        Task ret = new ToDo(description);
        return isDone ? ret.markDone() : ret;
    }

    public String getTaskType() {
        return "T";
    }

    @Override
    public String toString() {
        return String.format("[%s]%s", this.getTaskType(), super.toString());
    }
}
