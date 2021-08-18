public class Task {
    private String description;
    private boolean isDone;

    /**
     * @param description
     */
    public Task(String description) {
        this.description = description;
        isDone = false;
        System.out.println("Added: " + description + "\n");
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public void markAsDone() {
        if (this.isDone) {
            System.out.println("Item is already marked as done!\n");
        } else {
            this.isDone = true;
            System.out.println("Nice! I've marked this task as done:\n" + "[X] " + description + "\n");
        }
    }

    /**
     * @return
     */
    private String getStatusIcon() {
        return (isDone ? "X" : " ");
    }
}
