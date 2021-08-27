package duke.task;

import duke.io.TextColor;

public abstract class Task {
    protected boolean isDone;
    protected String name;

    /**
     * Constructor for a Task that sets its name and sets it to not done.
     *
     * @param name name of the task
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Marks the task as done
     */
    public void doTask() {
        isDone = true;
    }

    /**
     * Returns whether the task is done.
     *
     * @return true if task is done, false if task is not done
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns whether the task is expired. A task with no date is not expired.
     *
     * @return whether the task is expired
     */
    public boolean isExpired() {
        return false;
    }

    /**
     * Returns whether the task is happening today.
     * A task with no date is not happening today.
     *
     * @return whether the task is happening today
     */
    public boolean isToday() {
        return false;
    }

    /**
     * Returns the name of the task
     *
     * @return name of the task
     */
    public String getName() {
        return name;
    }

    /**
     * Returns what color the task should be listed with.
     *
     * @return The text color representing the task's status
     */
    public TextColor getListColor() {
        return isDone()
                ? TextColor.DEFAULT
                : isToday()
                ? TextColor.YELLOW
                : isExpired()
                ? TextColor.RED
                : TextColor.DEFAULT;
    }

    /**
     * Returns a string representation of the task, including its status and name
     *
     * @return string representation of the task
     */
    @Override
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + name;
    }

    /**
     * Returns a string compliant to the saveFile format
     *
     * @return String to be saved as a line in save.csv
     */
    public String getSaveString() {
        return (isDone ? "o," : "x,") + name;
    }
}
