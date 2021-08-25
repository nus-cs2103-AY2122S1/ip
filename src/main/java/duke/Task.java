package duke;

public class Task {
    protected final String taskDescription;
    protected boolean isDone;

    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
        this.isDone = false;
    }

    /**
     * Indicate that this task has been completed
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns true if the description of the task contains a specified pattern within it, and false otherwise.
     * @param pattern a string of the keyword to be checked against the description.
     * @return true if pattern is contained within task description.
     */
    public boolean hasKeyWord(String pattern) {
        return this.taskDescription.contains(pattern);
    }

    public String toDukeStoreFormat() {
        return String.format("%s | %s", this.isDone ? "1" : "0", this.taskDescription);
    }

    @Override
    public String toString() {
        return String.format("[%c] %s", isDone ? 'X' : ' ', taskDescription);
    }
}
