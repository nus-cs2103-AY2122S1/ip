package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Task {
    private String description;
    private boolean isDone;
    private LocalDateTime dateTimeToStartAfter;
    private Task taskToStartAfter;

    /**
     * A constructor method for a Task object.
     *
     * @param description the name of the Task
     */
    protected Task(String description) {
        this.description = description;
        this.isDone = false;
        taskToStartAfter = EmptyTask.EMPTY_TASK;
        dateTimeToStartAfter = LocalDateTime.MAX;
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
     * Returns the user-friendly information about this task.
     *
     * @return this task's information
     */
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Returns the full information about this task.
     *
     * @param tasks the TaskList that contains all current tasks
     * @return this task's full information
     */
    public String toStorageString(TaskList tasks) {
        return this.toString()
                + " / "
                + dateTimeToStartAfter.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
                + " / "
                + taskNumToStartAfter(tasks, taskToStartAfter);
    }

    private int taskNumToStartAfter(TaskList tasks, Task taskToStartAfter) {
        int taskNumToReturn = tasks.getTaskNum(taskToStartAfter);
        return taskNumToReturn == -1 ? 0 : taskNumToReturn;
    }

    /**
     * Sets this task to be done after a specific task.
     *
     * @param task task that precedes this task
     */
    public void setDoAfterTask(Task task) throws DukeException {
        if (this != task) {
            this.taskToStartAfter = task;
        } else {
            throw new DukeException("A task cannot be set to begin after itself!");
        }
    }

    /**
     * Sets this task to be done after a specific date time.
     *
     * @param dateTime this task should be done after this date and time
     */
    public void setDoAfterDateTime(LocalDateTime dateTime) {
        this.dateTimeToStartAfter = dateTime;
    }

    /**
     * Checks if this Task is after a specific Task.
     *
     * @param task the Task to be checked against
     * @return true if this Task is after the specified Task
     */
    public boolean isAfterTask(Task task) {
        return this.taskToStartAfter == task;
    }

    /**
     * Checks if this Task is after a specific date and time.
     *
     * @param dateTime the date and time to be checked against
     * @return true if this Task is after the specified date and time
     */
    public boolean isAfterDateTime(LocalDateTime dateTime) {
        return this.dateTimeToStartAfter.compareTo(dateTime) < 1;
    }

    /**
     * Checks if this Task is an EmptyTask.
     *
     * @return true if this Task is an EmptyTask
     */
    public abstract boolean isEmptyTask();
}
