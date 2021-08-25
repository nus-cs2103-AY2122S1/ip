package duke.task;

public abstract class Task {
    private final String name;
    private final boolean isCompleted;
    private final TaskType taskType;
    public static final EmptyTask EMPTY_TASK = EmptyTask.empty();

    Task(String name, TaskType taskType, boolean isCompleted) {
        this.taskType = taskType;
        this.name = name;
        this.isCompleted = isCompleted;
    }

    public static Task emptyTask() {
        return Task.EMPTY_TASK;
    }

    public String getName() {
        return this.name;
    }

    public boolean getCompleted() {
        return this.isCompleted;
    }

    public abstract Task updateName(String input);

    public abstract Task complete();

    public abstract String getDetails();

    public abstract String getLabel();

    public String getTaskTypeString() {
        return "[" + this.taskType.toString() + "]";
    }

    public TaskType getTaskType() {
        return this.taskType;
    }

    @Override
    public String toString() {
        return getDetails();
    }

    public static final class EmptyTask extends Task {

        EmptyTask() {
            super("", TaskType.EMPTY, false);
        }

        public static EmptyTask empty() {
            return new EmptyTask();
        }

        @Override
        public EmptyTask updateName(String input) {
            return this;
        }

        @Override
        public EmptyTask complete() {
            return this;
        }

        @Override
        public String getDetails() {
            return "Empty";
        }

        @Override
        public String getLabel() {
            return "Empty";
        }
    }

}
