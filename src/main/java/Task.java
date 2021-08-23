public abstract class Task {
    protected String taskName;
    protected boolean isDone;

    public Task(String taskName, boolean isDone) {
        this.taskName = taskName;
        this.isDone = isDone;
    }

    /**
     * Marks this particular task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Returns a string representation of the task to store within the save file.
     * @return A string representation of the task.
     */
    public abstract String parseToString();
}
