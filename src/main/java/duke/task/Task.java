package duke.task;

/**
 * Encapsulates a task in DukeList.
 */
public abstract class Task {
    private boolean completed;
    protected String name;

    public Task(String name) {
        this(name, false);
    }

    public Task(String name, boolean completed) {
        this.completed = completed;
        this.name = name;
    }

    /**
     * Mark the task as completed.
     */
    public void markCompleted() {
        this.completed = true;
    }

    /**
     * Returns whether the task is completed.
     */
    public boolean isCompleted() {
        return this.completed;
    }

    /**
     * Returns the name of the task.
     * 
     * @return name of the task
     */
    public String getName() {
        return this.name;
    }

    /**
     * Return date of the task. It could a deadline/date for the event.
     * 
     * @return date of task
     */
    public String getDate() {
        return null;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Task)
            return ((Task) obj).name.equals(this.name);
        return false;
    }

    @Override
    public String toString() {
        return "[" + (this.completed ? "X" : " ") + "] " + this.name;
    }

    public TaskType getType() {
        return TaskType.byTask(this.getClass());
    }

}
