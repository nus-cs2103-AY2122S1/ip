package me.yukun99.ip.commands;

import me.yukun99.ip.core.TaskList;
import me.yukun99.ip.core.Ui;

public class HelpCommand extends Command {
	public HelpCommand(String[] args, TaskList taskList, Ui ui) {
		super(args, taskList, ui);
	}

	@Override
	public void run() {
		ui.help();
	}
}
