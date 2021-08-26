package me.yukun99.ip.commands;

import me.yukun99.ip.core.DateTimePair;
import me.yukun99.ip.core.TaskList;
import me.yukun99.ip.core.Ui;
import me.yukun99.ip.exceptions.HelpBotDateTimeFormatException;

public class ListCommand extends Command {
	public ListCommand(String[] args, TaskList taskList, Ui ui) {
		super(args, taskList, ui);
	}

	@Override
	public void run() throws HelpBotDateTimeFormatException {
		if (args == null) {
			ui.list();
			return;
		}
		DateTimePair date = DateTimePair.parse(args[0]);
		ui.listByDate(date);
	}
}
