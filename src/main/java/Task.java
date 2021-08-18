class Task {

    private final String taskName;
    private final boolean isCompleted;

    Task(String itemName) {
        this.taskName = itemName;
        this.isCompleted = false;
    }

    private Task(Task oldTask) {
        this.taskName = oldTask.taskName;
        this.isCompleted = true;
    }

    Task markAsCompleted() {
        return new Task(this);
    }

    public String getTaskName() {
        return taskName;
    }

    @Override
    public String toString() {
        return "[" + (isCompleted ? "X": " ") + "] " + this.taskName;
    }
}
