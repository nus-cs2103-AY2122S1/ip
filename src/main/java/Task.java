public class Task {
    private String taskDescription;
    private boolean isDone;

    Task(String description, boolean isDone) {
        this.taskDescription = description;
        this.isDone = isDone;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String toString() {
        String done = isDone ? "X" : " ";
        return String.format("[%s] %s", done, taskDescription);
    }

}
