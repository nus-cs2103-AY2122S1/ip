package me.yukun99.ip.tasks;

import me.yukun99.ip.core.DateTimePair;
import me.yukun99.ip.core.TaskFinder;
import me.yukun99.ip.core.Ui;
import me.yukun99.ip.exceptions.HelpBotDateTimeFormatException;
import me.yukun99.ip.exceptions.HelpBotInvalidTaskTypeException;

import java.util.Objects;

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
	protected boolean done = false;

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
	public void setDone(Ui ui) {
		if (done) {
			ui.alreadyDone(this);
			return;
		}
		done = true;
		ui.done(this);
	}

	/**
	 * Marks a task as done without sending a message to the user.
	 */
	public void setDone() {
		done = true;
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
	 * Checks whether another Task object is equal to the current instance.
	 *
	 * @param o Task object to check against.
	 * @return Whether the other Object is equal to the current instance.
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null) {
			return false;
		}
		if (!(o instanceof Task)) {
			return false;
		}
		Task task = (Task) o;
		return name.equals(task.name);
	}

	/**
	 * Updates TaskFinder instance when task is added or deleted.
	 *
	 * @param taskFinder TaskFinder instance to be updated.
	 * @param delete True if task is being deleted, false otherwise.
	 */
	public void updateFinder(TaskFinder taskFinder, boolean delete) {
		if (!delete) {
			taskFinder.addTask(this, name);
		} else {
			taskFinder.deleteTask(this, name);
		}
	}

	/**
	 * Sends the task deletion message via the Ui class.
	 *
	 * @param ui Ui instance to send the deletion message from.
	 */
	public void deleteMessage(Ui ui) {
		ui.delete(this, done);
	}

	/**
	 * Gets the string representation of the task to be saved into a file.
	 *
	 * @return String representation of the task to be saved into a file.
	 */
	public String saveString() {
		return System.lineSeparator();
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, done);
	}

	/**
	 * Returns a String representation of the Task instance.
	 *
	 * @return String representation of the Task instance.
	 */
	@Override
	public String toString() {
		if (!done) {
			return PREFIX_NOT_DONE + name;
		}
		return PREFIX_DONE + name;
	}
}
