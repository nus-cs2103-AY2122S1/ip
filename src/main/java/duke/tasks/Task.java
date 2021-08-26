package duke.tasks;

public class Task {
    private String taskname;
    private boolean completed;

    public Task(String taskname) {
        this.taskname = taskname;
        this.completed = false;
    }

    public void markAsCompleted() {
        this.completed = true;
    }

    public boolean getCompleted() {
        return this.completed;
    }

    public String getTaskName() {
        return this.taskname;
    }

    @Override
    public String toString() {
        return this.taskname + " " + (this.completed ? "[X]" : "[ ]");
    }
}
