package Tasks;

/**
 * Task is a parent class of other specific types of tasks
 */
public class Task {
    private String description;
    private boolean isDone;

    /**
     * @param description The description of the task
     */
    public Task(String description) {
        this.description = description.trim();
        isDone = false;
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
        if (this.isDone) {
            System.out.println("Item is already marked as done!\n");
        } else {
            this.isDone = true;
            System.out.println("Nice! I've marked this task as done:\n" + "[X] " + description + "\n");
        }
    }

    private String getStatusIcon() {
        return (isDone ? "X" : " ");
    }
}

