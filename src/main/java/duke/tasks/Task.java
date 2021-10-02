package duke.tasks;

/** Class representing a task. */
public abstract class Task {
    /* name of the Task. */
    protected String taskName;
    /* Is the class done. */
    protected boolean isDone;

    /**
     * Constructor for Task.
     *
     * @param taskName Name of the task.
     */
    public Task(String taskName) {
        isDone = false;
        this.taskName = taskName;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", isDone ? "X" : " ", taskName);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Task)) {
            return false;
        }
        Task t = (Task) o;
        return taskName.equals(t.taskName) && isDone == t.isDone();
    }

    /**
     * Marks the current Task as done.
     */
    public void markDone() {
        isDone = true;
    }

    /**
     * Gets the isDone status of the current task
     *
     * @return True if task is done, false otherwise;
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns the name of this task
     * 
     * @return String representation of name
     */
    public String getName() {
        return taskName;
    }

    /**
     * Gets an identifier for the labelled task.
     *
     * @return A string identifier for this task.
     */
    public abstract String getIdentifier();

    /**
     * Gets all unique details of the task separated by a delimiter
     *
     * @param delimiter String to use for separation
     * @return Details of the task with a delimiter.
     */
    public abstract String getDetailsWithDelimiter(String delimiter);

    /**
     * Returns the next task to do after this one
     *
     * @return null by default as most tasks are one-time.
     */
    public Task getNextTask() {
        return null;
    }


}
