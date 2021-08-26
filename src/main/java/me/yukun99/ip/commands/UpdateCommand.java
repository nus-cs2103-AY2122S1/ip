package me.yukun99.ip.commands;

import me.yukun99.ip.core.Storage;
import me.yukun99.ip.core.TaskList;
import me.yukun99.ip.core.Ui;
import me.yukun99.ip.exceptions.HelpBotDateTimeFormatException;
import me.yukun99.ip.exceptions.HelpBotInvalidTaskException;
import me.yukun99.ip.exceptions.HelpBotInvalidTaskTypeException;
import me.yukun99.ip.tasks.Task;

public class UpdateCommand extends Command {
	Storage storage;

	public UpdateCommand(String[] args, TaskList taskList, Ui ui, Storage storage) {
		super(args, taskList, ui);
		this.storage = storage;
	}

	@Override
	public void run() throws HelpBotInvalidTaskException, HelpBotInvalidTaskTypeException {
		try {
			Task updated = taskList.updateTask(args[0], args[1]);
			ui.update(updated);
			storage.updateTasks();
		} catch (HelpBotDateTimeFormatException e) {
			ui.error(e);
		}
	}
}
