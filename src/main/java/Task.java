class Task {
    private String Description;
    private boolean done;

    /**
     * Private constructor for Task
     *
     * @param description is the string of the description of the given task
     */
    private Task(String description) {
        this.Description = description;
        this.done = false;
    }

    /**
     * Public method to call the private constructor of Task
     *
     * @param description is the string of the description of the given task
     * @return A new Task Object with the given string description
     */
    public static Task createTask(String description) {
        return new Task(description);
    }

    /**
     * Getter for getting the description of the task
     *
     * @return a String of the description of the task
     */
    public String getDescription() {
        return this.Description;
    }

    /**
     * Getter for finding out whether the task is marked as completed or not
     *
     * @return a boolean on whether the task is marked as completed
     */
    public boolean getProgress() {
        return this.done;
    }

    /**
     * Setter for marking the task as complete
     */
    public void setComplete() {
        this.done = true;
    }
}
