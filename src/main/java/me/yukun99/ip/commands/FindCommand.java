package me.yukun99.ip.commands;

import me.yukun99.ip.core.TaskList;
import me.yukun99.ip.core.Ui;
import me.yukun99.ip.exceptions.HelpBotIllegalArgumentException;

public class FindCommand extends Command {
	public FindCommand(String[] args, TaskList taskList, Ui ui) {
		super(args, taskList, ui);
	}

	@Override
	public void run() throws HelpBotIllegalArgumentException {
		if (args.length == 0) {
			throw new HelpBotIllegalArgumentException(null);
		}
		ui.findByWord(args[0]);
	}
}
