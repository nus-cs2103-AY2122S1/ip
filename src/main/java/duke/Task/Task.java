package duke.Task;

/**
 * Class describing the task object
 */
public class Task {
    private String taskDescription;
    private boolean isDone;

    public Task(String description, boolean isDone) {
        this.taskDescription = description;
        this.isDone = isDone;
    }

    public void markAsDone() {
        this.isDone = true;
    }
    @Override
    public String toString() {
        String done = isDone ? "X" : " ";
        return String.format("[%s] %s", done, taskDescription);
    }

}
