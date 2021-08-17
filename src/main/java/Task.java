public class Task {
    protected String title;
    protected boolean done;
    protected char typeIndicator;

    /**
     * A constructor for a Task.
     *
     * @param title a String representing the user-input title of the task.
     */
    public Task(String title) throws DukeException {
        if (title.length() == 0) {
            throw new DukeException("The description of a Task cannot be empty.");
        }
        this.title = title;
        this.done = false;
    }

    /**
     * Marks a task as done.
     */
    public void completeTask() {
        this.done = true;
    }

    /**
     * Returns the string representation of a task.
     *
     * @return A string describing the task.
     */
    @Override
    public String toString() {
        String doneIndicator = this.done ? "x" : " ";
        return String.format("[%s][%s] %s", typeIndicator, doneIndicator, this.title);
    }
}
