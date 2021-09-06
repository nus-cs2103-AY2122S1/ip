package duke;

import java.time.LocalDateTime;

public class Task {
    private String description;
    private boolean isDone;
    private LocalDateTime doAfterDateTime;
    private Task doAfterTask;

    /**
     * A constructor method for a Task object.
     *
     * @param description the name of the Task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        doAfterTask = new Task("empty");
        doAfterDateTime = LocalDateTime.MAX;
    }

    /**
     * Gets the status of this task.
     *
     * @return "X" if task is done, a space otherwise
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Checks if a string matches a part of this task's description.
     *
     * @param input the string to check with this task's description
     * @return true if part of this description contains the string, false otherwise
     */
    public boolean checkDescription(String input) {
        return description.contains(input);
    }


    /**
     * Marks this task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Returns the information about this task.
     *
     * @return this task's information
     */
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Sets this task to be done after a specific task.
     *
     * @param task task that precedes this task
     */
    public void setDoAfterTask(Task task) {
        this.doAfterTask = task;
    }

    /**
     * Sets this task to be done after a specific date time.
     *
     * @param dateTime this task should be done after this date and time
     */
    public void setDoAfterDateTime(LocalDateTime dateTime) {
        this.doAfterDateTime = dateTime;
    }
}
