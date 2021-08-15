package me.yukun99.ip.exceptions;

import me.yukun99.ip.tasks.Task;

public class HelpBotInvalidTaskTypeException extends HelpBotException {
	private final Task.TaskType type;

	public HelpBotInvalidTaskTypeException(String errorMessage, Task.TaskType type) {
		super(errorMessage);
		this.type = type;
	}

	public String toString() {
		return "The operation could not be performed on TaskType: " + type;
	}
}
