package duke.Task;
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

    public boolean contains(String keyword) {
        return this.taskDescription.contains(keyword);
    }

    @Override
    public String toString() {
        String done = isDone ? "X" : " ";
        return String.format("[%s] %s", done, taskDescription);
    }

}
