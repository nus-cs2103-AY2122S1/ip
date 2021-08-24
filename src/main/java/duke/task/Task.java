package duke.task;

/**
 * The parent of all Task type classes in Duke.
 */
public abstract class Task {

    /** The name of the task to be stored. */
    private final String name;

    /** The boolean flag that represents the completion of a task. */
    private final boolean isCompleted;

    /** The various types of tasks, as denoted by TaskType. */
    private final TaskType taskType;

    /** The EmptyTask object, which will serve as a placeholder for empty Tasks. */
    public static final EmptyTask EMPTY_TASK = EmptyTask.empty();

    /**
     * The constructor of Task
     * @param name Name of task
     * @param taskType Type of task
     * @param isCompleted The completion status of the task
     */
    Task(String name, TaskType taskType, boolean isCompleted) {
        this.taskType = taskType;
        this.name = name;
        this.isCompleted = isCompleted;
    }

    /**
     * An EmptyTask factory method
     * @return EmptyTask
     */
    public static Task emptyTask() {
        return Task.EMPTY_TASK;
    }

    /**
     * Getter that retrieves task name.
     * @return name of task
     */
    public String getName() {
        return this.name;
    }

    /**
     * Getter that retrieves completion status.
     * @return isCompleted
     */
    public boolean getCompleted() {
        return this.isCompleted;
    }

    /**
     * Abstract method that updates the name of the task.
     * @param input The new name to update to.
     * @return Task
     */
    public abstract Task updateName(String input);

    /**
     * Abstract method that task as completed.
     * @return Task
     */
    public abstract Task complete();

    /**
     * Abstract method that returns the details of the task.
     * @return Task
     */
    public abstract String details();

    /**
     * Abstract method that returns the label of the task.
     * @return Task
     */
    public abstract String getLabel();

    /**
     * Method that returns the string representation of the TaskType.
     * @return String that represents the TaskType of the task.
     */
    public String taskTypeString() {
        return "[" + this.taskType.toString() + "]";
    }

    /**
     * Method that returns the TaskType.
     * @return TaskType of the task.
     */
    public TaskType taskType() {
        return this.taskType;
    }

    /**
     * Overrides toString from Object.
     * @return string
     */
    @Override
    public String toString() {
        return details();
    }

    /**
     * The EmptyTask placeholder task.
     */
    public static final class EmptyTask extends Task {

        /**
         * Constructor that produces EmptyTask.
         */
        EmptyTask() {
            super("", TaskType.EMPTY, false);
        }

        /**
         * EmptyTask factory method
         * @return EmptyTask
         */
        public static EmptyTask empty() {
            return new EmptyTask();
        }

        /**
         * Method that updates the name, in this case it just returns the EmptyTask
         * @return EmptyTask
         */
        @Override
        public EmptyTask updateName(String input) {
            return this;
        }

        /**
         * Method that completes, in this case it just returns the EmptyTask
         * @return EmptyTask
         */
        @Override
        public EmptyTask complete() {
            return this;
        }

        @Override
        public String details() {
            return "Empty";
        }

        @Override
        public String getLabel() {
            return "Empty";
        }
    }

}
