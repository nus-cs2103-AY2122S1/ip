package duke.task;

import duke.exception.DukeException;
import duke.exception.TimedTaskDateInputException;

/**
 * An abstract class representing the Task Type object.
 */
public abstract class Task {

    /** An empty task to ensure the existence of only one empty task.*/
    public static final EmptyTask EMPTY_TASK = EmptyTask.empty();
    private final String name;
    private final boolean isCompleted;
    private final TaskType taskType;

    Task(String name, TaskType taskType, boolean isCompleted) {
        this.taskType = taskType;
        this.name = name;
        this.isCompleted = isCompleted;
    }

    /**
     * Returns the empty task.
     * @return the empty task
     */
    public static Task emptyTask() {
        return Task.EMPTY_TASK;
    }

    /**
     * Returns the name of the task.
     * @return name of the task.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the completion status of the task.
     * @return completion status of the task.
     */
    public boolean getCompleted() {
        return this.isCompleted;
    }

    public abstract Task updateName(String input) throws DukeException;

    public abstract Task updateDateTime(String dateTime) throws DukeException;

    public abstract Task complete() throws TimedTaskDateInputException;

    public abstract String getDetails();

    public abstract String getLabel();

    /**
     * Produces the string form of the task type
     * @return string form of the task type
     */
    public String getTaskTypeString() {
        return "[" + this.taskType.toString() + "]";
    }

    /**
     * Returns the TaskType
     * @return taskType
     */
    public TaskType getTaskType() {
        return this.taskType;
    }

    /**
     * Returns the string version of the checkbox.
     * @return checkbox
     */
    public String getCheckBox() {
        return "[" + (getCompleted() ? "X" : " ") + "]";
    }

    @Override
    public String toString() {
        return getDetails();
    }

    /**
     * An Empty Task
     */
    public static final class EmptyTask extends Task {

        EmptyTask() {
            super("", TaskType.EMPTY, false);
        }

        /**
         * Factory method to obtain an empty task.
         * @return EmptyTask
         */
        public static EmptyTask empty() {
            return new EmptyTask();
        }

        /**
         * Return the same empty task.
         * @param input arbitrary input
         * @return the same empty task
         */
        @Override
        public EmptyTask updateName(String input) {
            return this;
        }

        /**
         * Return the same empty task.
         * @param dateTime arbitrary dateTime
         * @return the same empty task
         */
        @Override
        public Task updateDateTime(String dateTime) {
            return this;
        }

        /**
         * Return the same empty task.
         * @return the same empty task
         */
        @Override
        public EmptyTask complete() {
            return this;
        }

        /**
         * Return string which represents empty task.
         * @return string which represents empty task
         */
        @Override
        public String getDetails() {
            return "Empty";
        }

        /**
         * Return string which represents empty task.
         * @return string which represents empty task
         */
        @Override
        public String getLabel() {
            return "Empty";
        }
    }

}
