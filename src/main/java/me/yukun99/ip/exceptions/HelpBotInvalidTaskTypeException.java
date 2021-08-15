package me.yukun99.ip.exceptions;

import me.yukun99.ip.tasks.Task;

public class HelpBotInvalidTaskTypeException extends HelpBotException {
	private final Task.TaskType type;

	public HelpBotInvalidTaskTypeException(Task.TaskType type) {
		super();
		this.type = type;
	}

	public String toString() {
		return super.toString()
				+ "\nThe operation could not be performed on task type: " + type.toString().toLowerCase();
	}
}
