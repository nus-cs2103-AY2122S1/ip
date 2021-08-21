package me.yukun99.ip.tasks;

import me.yukun99.ip.core.Ui;
import me.yukun99.ip.exceptions.HelpBotInvalidTaskTypeException;

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
	private final String name;
	private boolean done = false;

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
		if (done) {
			Ui.alreadyDone(this);
			return;
		}
		done = true;
		Ui.done(this);
	}

	/**
	 * Updates the date of an event or deadline task.
	 *
	 * @param date New date to be updated.
	 * @throws HelpBotInvalidTaskTypeException If task does not contain a date to be updated.
	 */
	public abstract void updateDate(String date) throws HelpBotInvalidTaskTypeException;

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
		return this.name.equals(task.name);
	}

	public void deleteMessage(Ui ui) {
		ui.delete(this, done);
	}

	/**
	 * Returns a String representation of the Task instance.
	 *
	 * @return String representation of the Task instance.
	 */
	@Override
	public String toString() {
		if (!done) {
			return PREFIX_NOT_DONE + this.name;
		}
		return PREFIX_DONE + this.name;
	}
}
