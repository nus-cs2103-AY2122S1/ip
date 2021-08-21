public abstract class Task {
    private String name;
    private boolean completed;
    private final TaskType taskType;

    public Task(String name, TaskType taskType) {
        this.taskType = taskType;
        this.name = name;
        this.completed = false;
    }

    public String getName() {
        return this.name;
    }

    public boolean getCompleted() {
        return this.completed;
    }

    public void updateName(String input) {
        this.name = input;
    }

    public void markAsCompleted() {
        this.completed = true;
    }

    public String details() {
        String checkbox = "[" + (getCompleted() ? "X" : " ") + "]";
        return taskType() + checkbox + " " + this.getName();
    }

    public String taskType() {
        return "[" + this.taskType.toString() + "]";
    }

    @Override
    public String toString() {
        return details();
    }

}
