package Tasks;

/**
 * Task is a parent class of other specific types of tasks
 */
public abstract class Task {
    private String description;
    private int isDone;

    /**
     * @param description The description of the task
     */
    public Task(String description) {
        this.description = description.trim();
        isDone = 0;
        System.out.println("Added: " + this.description);
    }

    public Task(String description, int isDone) {
        this.description = description.trim();
        this.isDone = isDone;
        System.out.println("Added: " + this.description);
    }

    /**
     * A public toString method to return the task description with the checkbox for toggling completion
     *
     * @return the string representation of a task
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Method to toggle the isDone variable, to display to the user if a task is marked as done or not
     */
    public void markAsDone() {
        if (this.isDone == 1) {
            System.out.println("Item is already marked as done!\n");
        } else {
            this.isDone = 1;
            System.out.println("Nice! I've marked this task as done:\n" + "[X] " + description + "\n");
        }
    }

    protected String getDescription() {
        return this.description;
    }

    protected int getIsDone() {
        return isDone;
    }

    public abstract String toDataString();

    private String getStatusIcon() {
        return (isDone == 1 ? "X" : " ");
    }
}

