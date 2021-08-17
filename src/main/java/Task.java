public class Task {
    private String title;
    private boolean done;

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
        return "[" + doneIndicator + "] " + this.title;
    }
}
