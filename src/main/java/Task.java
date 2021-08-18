/**
 * A Task with ability to mark as done.
 *
 * @author KelvinSoo
 */
public class Task {
    private boolean isDone;
    private final String description;
    private static int totalTask;
    private final int taskID;

    public Task(String description) {
        this.description = description;
        isDone = false;
        totalTask++;
        taskID = totalTask;
    }

    public int getID() {
        return taskID;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    public String getDescription() {
        return description;
    }

    public void markASDone() {
        isDone = true;
    }
}
