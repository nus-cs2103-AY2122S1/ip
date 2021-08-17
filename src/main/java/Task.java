public class Task {
    protected String title;
    protected boolean done;
    protected char typeIndicator;

    /**
     * A constructor for a Task.
     *
     * @param title a String representing the user-input title of the task.
     */
    public Task(String title) {
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
