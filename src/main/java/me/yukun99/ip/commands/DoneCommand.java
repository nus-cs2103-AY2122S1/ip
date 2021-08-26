package me.yukun99.ip.commands;

import me.yukun99.ip.core.TaskList;
import me.yukun99.ip.core.Ui;
import me.yukun99.ip.exceptions.HelpBotInvalidTaskException;

public class DoneCommand extends Command {
	public DoneCommand(String[] args, TaskList taskList, Ui ui) {
		super(args, taskList, ui);
	}

	@Override
	public void run() throws HelpBotInvalidTaskException {
		taskList.doneTask(args[0], ui);
	}
}
