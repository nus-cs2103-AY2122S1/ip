package me.yukun99.ip.commands;

import me.yukun99.ip.core.TaskList;
import me.yukun99.ip.core.Ui;
import me.yukun99.ip.exceptions.HelpBotIllegalArgumentException;
import me.yukun99.ip.exceptions.HelpBotInvalidTaskTypeException;

public abstract class Command {
	protected final String[] args;
	protected final TaskList taskList;
	protected final Ui ui;

	public Command(String[] args, TaskList taskList, Ui ui) {
		this.args = args;
		this.taskList = taskList;
		this.ui = ui;
	}

	public abstract void run() throws HelpBotInvalidTaskTypeException, HelpBotIllegalArgumentException;
}
