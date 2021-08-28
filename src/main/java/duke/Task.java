package duke;

/**
 * Task is the abstract class that describes the basic details of all tasks.
 */
public abstract class Task {
    private String taskName;
    private TaskType taskType;
    private boolean isDone;

    /**
     * Creates a String object that contains all the task details
     * that is to be saved in the save file.
     *
     * @return the String object containing the task details.
     */
    public abstract String toSaveFormat();

    /**
     * Class constructor specifying the name and type of the task
     * and whether the task is done.
     *
     * @param taskName the name of the task.
     * @param taskType the type of task.
     * @param isDone whether the task is done.
     */
    public Task(String taskName, TaskType taskType, boolean isDone) {
        this.taskName = taskName;
        this.taskType = taskType;
        this.isDone = isDone;
    }

    /**
     * Class constructor specifying the name and type of the task.
     * The task is initialised as not done.
     *
     * @param taskName the name of the task.
     * @param taskType the type of task.
     */
    public Task(String taskName, TaskType taskType) {
        this(taskName, taskType, false);
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    @Override
    public String toString() {
        String checked = isDone ? "X" : " ";
        return String.format("[%s] %s", checked, taskName);
    }
}

;
