package me.yukun99.ip.tasks;

import me.yukun99.ip.exceptions.HelpBotInvalidTaskTypeException;

public class ToDo extends Task {
	public ToDo(String name) {
		super(name);
	}

	protected void updateDate(String date) throws HelpBotInvalidTaskTypeException {
		throw new HelpBotInvalidTaskTypeException(TaskType.TODO);
	}

	@Override
	public String toString() {
		return " [T]" + super.toString();
	}
}
