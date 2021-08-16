public class Task {
    private boolean isDone = false;
    private String name;

    Task(String name) {
        this.name = name;
    }

    /**
     * Returns a status string indicating state of completion.
     *
     * @return A status string for the task.
     */
    public String getCross() {
        if (isDone) {
            return "[X]";
        } else {
            return "[]";
        }
    }

    /**
     * Toggles completion status.
     */
    public void toggleDone() {
        this.isDone = true;
    }

    /**
     * Shows completion status appended with name.
     *
     * @return A status string containing name.
     */
    public String showStatus() {
        return this.getCross() + " " + this.name;
    }

    @Override
    public String toString() {
        return name;
    }

}
