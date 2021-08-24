import java.io.FileWriter;
import java.io.IOException;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a status icon.
     *
     * @return X if task is done, an empty space if it is not
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks a task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }

    /**
     * Prints a message specifying what task has just been added.
     *
     * @param size the current size of the task list
     */
    public void addTaskMsg(int size) {
        System.out.println(
                "I have added the task!\n  "
                + this
                + "\nNow you have " + size + " tasks left!");
    }

    /**
     * Prints a message specifying what task has been removed.
     *
     * @param size the current size of the task list
     */
    public void removeTaskMsg(int size) {
        System.out.println(
                "I have removed the task:\n  "
                        + this
                        + "\nNow you have " + size + " tasks left!");
    }

    public abstract void writeToFile(FileWriter myWriter) throws IOException;

}
