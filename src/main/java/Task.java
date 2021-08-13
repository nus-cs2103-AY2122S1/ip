public class Task {

    String name;
    boolean isDone;

    /**
     * Constructor for a Task object
     *
     * @param name the name of the task
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Returns a string representing the task
     *
     * @return a string representing the task
     */
    @Override
    public String toString() {
        if (isDone) {
            return "[X] " + this.name;
        }
        return "[ ] " + this.name;
    }

    /**
     * Marks a task as done
     */
    public void markDone() {
        this.isDone = true;
    }

}
