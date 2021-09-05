package duke;

/**
 * The abstract Task class representing a task.
 */
public abstract class Task {
    private String description;
    private boolean isDone;
    private char icon;

    /**
     * Constructor for a Task object.
     *
     * @param description The description of the task.
     * @param icon Marker that indicates the type of task.
     */
    public Task(String description, char icon) {
        this.description = description;
        this.icon = icon;
        this.isDone = false;
    }

    /**
     * A method that gets a marker that indicates if task is complete.
     *
     * @return A marker that indicates if task is complete.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * A method that returns the icon for the respective task.
     *
     * @return The task icon.
     */
    public char getTaskIcon() {
        return icon;
    }

    /**
     * A method that marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /** A method that indicates whether the task description contains a keyword.
     *
     * @param keyword The keyword to be searched for in the description.
     * @return Boolean indicating whether the keyword is found in the description.
     */
    public boolean contains(String keyword) {
        return description.toLowerCase().indexOf(keyword.toLowerCase()) != -1;
    }

    /**
     * A method that converts the task data into a suitable format to be saved in a save file.
     *
     * @return The formatted data as a string.
     */
    public String toData() {
        String isDoneNum = isDone ? "1" : "0";
        return String.format("%s~S~%s~S~%s", icon, isDoneNum, description);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), description);
    }
}
