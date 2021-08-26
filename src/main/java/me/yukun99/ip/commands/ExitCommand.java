package me.yukun99.ip.commands;

import me.yukun99.ip.HelpBot;
import me.yukun99.ip.core.TaskList;
import me.yukun99.ip.core.Ui;

public class ExitCommand extends Command {
	private final HelpBot helpBot;

	public ExitCommand(String[] args, TaskList taskList, Ui ui, HelpBot helpBot) {
		super(args, taskList, ui);
		this.helpBot = helpBot;
	}

	@Override
	public void run() {
		helpBot.exit();
	}
}
