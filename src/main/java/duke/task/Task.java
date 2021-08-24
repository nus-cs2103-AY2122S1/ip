package duke.task;

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
<<<<<<< HEAD
     * Returns a String representation of the task to store within the save file.
     *
     * @return The String representation of the task.
=======
     * Returns a string representation of the task to store within the save file.
     *
     * @return The string representation of the task.
>>>>>>> Level-9
     */
    public abstract String parseToString();

    /**
     * Returns a boolean representing if the given String is a substring of the task's description.
     *
     * @param string The given String.
     * @return True if the given String is a substring, and false otherwise.
     */
    public boolean hasSubString(String string) {
        return this.taskName.contains(string);
    }
}
