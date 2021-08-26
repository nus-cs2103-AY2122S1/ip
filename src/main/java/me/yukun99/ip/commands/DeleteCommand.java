package me.yukun99.ip.commands;

import me.yukun99.ip.core.Storage;
import me.yukun99.ip.core.TaskList;
import me.yukun99.ip.core.Ui;
import me.yukun99.ip.exceptions.HelpBotInvalidTaskException;
import me.yukun99.ip.tasks.Task;

public class DeleteCommand extends Command {
	Storage storage;

	public DeleteCommand(String[] args, TaskList taskList, Ui ui, Storage storage) {
		super(args, taskList, ui);
		this.storage = storage;
	}

	@Override
	public void run() throws HelpBotInvalidTaskException {
		Task deleted = taskList.deleteTask(args[0]);
		deleted.deleteMessage(ui);
		storage.updateTasks();
	}
}
