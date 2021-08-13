package components;

public class Task {
    private String taskDescription;

    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    @Override
    public String toString() {
        return this.taskDescription;
    }
}
