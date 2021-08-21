package me.yukun99.ip.commands;

import me.yukun99.ip.core.TaskList;
import me.yukun99.ip.core.Ui;
import me.yukun99.ip.exceptions.HelpBotInvalidTaskException;
import me.yukun99.ip.tasks.Task;

public class DeleteCommand extends Command {
	public DeleteCommand(String[] args, TaskList taskList, Ui ui) {
		super(args, taskList, ui);
	}

	@Override
	public void run() throws HelpBotInvalidTaskException {
		Task deleted = this.taskList.deleteTask(args[0]);
		deleted.deleteMessage(ui);
	}
}
