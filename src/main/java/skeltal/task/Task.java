package skeltal.task;

public class Task {

    private boolean completed;
    private String task;

    /**
     * A constructor that initialises a Task object.
     * @param task The Task object.
     */
    public Task(String task) {
        this.completed = false;
        this.task = task;
    }

    /**
     * A method to get the task description.
     * @return The description of the task.
     */
    public String getTaskDescription() {
        return task;
    }

    /**
     * Returns a String representation of the task that is suitable for loading.
     * @return A loadable String representation of the Task object.
     */
    public String store() {
        int done = this.isCompleted() ? 1 : 0;
        return done + " | " + this.getTaskDescription();
    }


    /**
     * Returns true if the task has been completed, else returns false.
     * @return A boolean representation of whether the task has been completed.
     */
    public boolean isCompleted() {
        return this.completed;
    }

    /**
     * Sets the task to a completed state.
     */
    public void setComplete() {
        this.completed = true;
    }

    /**
     * Returns a String representation of the task suitable for printing.
     * Eg "[X][ ] Task".
     * @return A String representation of the task.
     */
    @Override
    public String toString() {
        String tick = this.isCompleted() ? "[X] " : "[ ] ";
        int num = TaskList.getIndex(this) + 1;
        String str = tick + num + ". " + this.task;
        return str;
    }
}

