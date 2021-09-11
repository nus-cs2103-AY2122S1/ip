package me.yukun99.ip.tasks;

import me.yukun99.ip.core.DateTimePair;
import me.yukun99.ip.core.TaskFinder;
import me.yukun99.ip.core.TaskList;
import me.yukun99.ip.exceptions.HelpBotDateTimeFormatException;
import me.yukun99.ip.exceptions.HelpBotInvalidTaskTypeException;
import me.yukun99.ip.ui.Message;

/**
 * Tasks stored in our task list.
 */
public abstract class Task {
    // Task type list
    public enum Type {
        TODO,
        DEADLINE,
        EVENT,
    }

    // Constants
    private static final String PREFIX_NOT_DONE = "[ ] ";
    private static final String PREFIX_DONE = "[X] ";

    // Constants/Variables related to the instance
    protected final String name;
    protected boolean isDone = false;

    /**
     * Constructor for a Task instance.
     *
     * @param name Name of the task to be created.
     */
    public Task(String name) {
        this.name = name;
    }

    /**
     * Marks a task as done.
     */
    public void setDone() {
        isDone = true;
    }

    /**
     * Checks if a task is done.
     *
     * @return Whether a task is done.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Updates the date of an event or deadline task.
     *
     * @param date New date to be updated.
     * @throws HelpBotInvalidTaskTypeException If task does not contain a date to be updated.
     * @throws HelpBotDateTimeFormatException If task date could not be parsed correctly from String.
     */
    public abstract void updateDate(String date) throws HelpBotInvalidTaskTypeException,
            HelpBotDateTimeFormatException;

    /**
     * Gets the date and time of the task.
     *
     * @return Date and time pair of the task.
     * @throws HelpBotInvalidTaskTypeException If task does not contain a date.
     */
    public abstract DateTimePair getDate() throws HelpBotInvalidTaskTypeException;

    /**
     * Gets message sent when user deletes a task.
     *
     * @param taskList TaskList that task is being deleted from.
     * @return Message sent when user deletes a task.
     */
    public String getDeleteMessage(TaskList taskList) {
        return Message.getDeleteMesage(this, isDone, taskList);
    }

    /**
     * Updates TaskFinder instance when task is added or deleted.
     *
     * @param taskFinder TaskFinder instance to be updated.
     * @param toDelete True if task is being deleted, false otherwise.
     */
    public void updateFinder(TaskFinder taskFinder, boolean toDelete) {
        if (!toDelete) {
            taskFinder.addTask(this, name);
        } else {
            taskFinder.deleteTask(this, name);
        }
    }

    /**
     * Gets the string representation of the task to be saved into a file.
     *
     * @return String representation of the task to be saved into a file.
     */
    public String saveString() {
        return System.lineSeparator();
    }

    /**
     * Returns a String representation of the Task instance.
     *
     * @return String representation of the Task instance.
     */
    @Override
    public String toString() {
        if (!isDone) {
            return PREFIX_NOT_DONE + name;
        }
        return PREFIX_DONE + name;
    }
}
