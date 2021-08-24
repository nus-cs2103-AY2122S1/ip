package duke.task;

/**
 * Represents a Task that the user have added to the task list.
 */
public class Task {
    private String name;
    private boolean completed;
    private String taskType = "G";

    /**
     * Creates a new Task object.
     *
     * @param name Name of the task.
     */
    public Task(String name) {
        this.name = name;
        this.completed = false;
    }

    /**
     * Creates a new Task object.
     * Allows specifying the TaskType
     *
     * @param name Name of the task.
     * @param taskType String representing the type of the task.
     */
    public Task(String name, String taskType) {
        this.name = name;
        this.completed = false;
        this.taskType = taskType;
    }

    /**
     * Creates a new Task object.
     * Allows specifying the TaskType, and whether it is completed.
     *
     * @param name Name of the task.
     * @param completed Boolean representing whether task is completed.
     * @param taskType String representing the type of the task.
     */
    public Task(String name, boolean completed, String taskType) {
        this.name = name;
        this.completed = completed;
        this.taskType = taskType;
    }

    /**
     * Returns the name attribute.
     *
     * @return Name of the task.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the task to be completed.
     *
     * @return True.
     */
    public boolean completeTask() {
        this.completed = true;
        return true;
    }

    /**
     * Returns whether the task is completed.
     *
     * @return True if task is completed, false otherwise.
     */
    public boolean isCompleted() {
        return this.completed;
    }

    /**
     * Returns the taskType of the task.
     *
     * @return String representing the task type.
     */
    public String getTaskType() {
        return taskType;
    }

    /**
     * Returns the string to be used for saving the task on a file.
     *
     * @return String representing the Task.
     */
    public String getSaveFormat() {
        return String.format("%s|%s|%s", this.taskType, this.completed ? "c" : "i", this.name);
    }
}
