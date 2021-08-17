public abstract class Task {
    protected boolean isDone = false;
    protected String name;

    Task(String name) {
        this.name = name;
    }

    /**
     * Returns a status string indicating state of completion.
     *
     * @return A status string for the task.
     */
    public String printCompletionStatus() {
        if (isDone) {
            return "[X]";
        } else {
            return "[]";
        }
    }

    /**
     * Returns a status string indicating type of task.
     *
     * @return An indicator string for the type of task.
     */
    public abstract String printType();

    /**
     * Toggles completion status.
     */
    public void toggleDone() {
        this.isDone = true;
    }

    /**
     * Shows all statuses appended with name.
     *
     * @return A status string containing name.
     */
     public String showStatus() {
        return this.printType() + this.printCompletionStatus() + " " + this.name;
     }

    @Override
    public String toString() {
        return name;
    }

}
