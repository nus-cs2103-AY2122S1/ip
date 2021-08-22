public abstract class Task {
    private final String name;
    private final boolean isCompleted;
    private final TaskType taskType;

    Task(String name, TaskType taskType, boolean isCompleted) {
        this.taskType = taskType;
        this.name = name;
        this.isCompleted = isCompleted;
    }

    public String getName() {
        return this.name;
    }

    public boolean getCompleted() {
        return this.isCompleted;
    }

    public abstract Task updateName(String input);

    public abstract Task complete();

    public abstract String details();

    public String taskType() {
        return "[" + this.taskType.toString() + "]";
    }

    @Override
    public String toString() {
        return details();
    }

}
