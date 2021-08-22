package me.yukun99.ip.commands;

import me.yukun99.ip.core.Storage;
import me.yukun99.ip.core.TaskList;
import me.yukun99.ip.core.Ui;
import me.yukun99.ip.exceptions.HelpBotIllegalArgumentException;
import me.yukun99.ip.tasks.Deadline;
import me.yukun99.ip.tasks.Event;
import me.yukun99.ip.tasks.Task;
import me.yukun99.ip.tasks.ToDo;

import static me.yukun99.ip.tasks.Task.Type;

public class AddCommand extends Command {
	private final Type type;
	private Storage storage;

	public AddCommand(String[] args, TaskList taskList, Ui ui, Type type, Storage storage) {
		super(args, taskList, ui);
		this.type = type;
		this.storage = storage;
	}

	@Override
	public void run() throws HelpBotIllegalArgumentException {
		Task task;
		switch (this.type) {
		case TODO:
			task = new ToDo(this.args[0]);
			break;
		case DEADLINE:
			task = new Deadline(this.args[0], this.args[1]);
			break;
		case EVENT:
			task = new Event(this.args[0], this.args[1]);
			break;
		default:
			throw new HelpBotIllegalArgumentException(this.args[0]);
		}
		this.taskList.addTask(task);
		this.ui.add(task);
		this.storage.saveTask(task);
	}
}
