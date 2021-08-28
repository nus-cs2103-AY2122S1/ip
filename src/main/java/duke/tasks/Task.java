package duke.tasks;

public class Task {
    private String taskname;
    private boolean isCompleted;

    public Task(String taskname) {
        this.taskname = taskname;
        this.isCompleted = false;
    }

    public void markAsCompleted() {
        this.isCompleted = true;
    }

    public boolean getCompleted() {
        return this.isCompleted;
    }

    public String getTaskName() {
        return this.taskname;
    }

    @Override
    public String toString() {
        return this.taskname + " " + (this.isCompleted ? "[X]" : "[ ]");
    }
}
