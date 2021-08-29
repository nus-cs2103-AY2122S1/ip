package duke.task;

/**
 * The Task class represents the tasks in the task list.
 *
 * @author Loh Wen Hao Aaron
 *
 */
public class Task {
    protected String description;
    protected String date;
    protected String time;
    protected boolean isDone;

    /**
     * This is the constructor for a task which takes in the description,
     * date and time.
     *
     * @param description The String description for the task.
     * @param date The String date for the task.
     * @param time The String time for the task.
     */
    public Task(String description, String date, String time) {
        this.description = description;
        this.date = date;
        this.time = time;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        isDone = true;
    }

    /**
     * Returns a string representation of the task that is stored in the save file.
     *
     * @return A string representation of the task.
     */
    public String getReadableString() {
        return "";
    }

    /**
     * Returns the date of a task, formatted.
     *
     * @return A formatted string representation of the date of the task.
     */
    public String getFormattedDate() {
        return "";
    }

    /**
     * Returns the time of a task, formatted.
     *
     * @return A formatted string representation of the time of the task.
     */
    public String getFormattedTime() {
        return "";
    }

    public String getDate() {
        return this.date;
    }

    public String getDesc() {
        return this.description;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }
}
