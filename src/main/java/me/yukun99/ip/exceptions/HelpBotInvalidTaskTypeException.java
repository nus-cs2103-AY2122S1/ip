package me.yukun99.ip.exceptions;

import me.yukun99.ip.tasks.Task;

/**
 * Exception resulting from user trying to perform invalid changes on certain task types.
 */
public class HelpBotInvalidTaskTypeException extends HelpBotException {
	private final Task.Type type;

	/**
	 * Constructor for a HelpBotInvalidTaskTypeException instance.
	 *
	 * @param type Type of task user is trying to perform changes on.
	 */
	public HelpBotInvalidTaskTypeException(Task.Type type) {
		super();
		this.type = type;
	}

	public String toString() {
		return super.toString()
				+ "\nThe operation could not be performed on task type: " + type.toString().toLowerCase();
	}
}
