package me.yukun99.ip.commands;

import me.yukun99.ip.core.TaskList;
import me.yukun99.ip.core.Ui;
import me.yukun99.ip.exceptions.HelpBotDateTimeFormatException;
import me.yukun99.ip.exceptions.HelpBotIllegalArgumentException;
import me.yukun99.ip.exceptions.HelpBotInvalidTaskTypeException;

import java.util.Arrays;
import java.util.Objects;

/**
 * Commands sent to the HelpBot.
 */
public abstract class Command {
	// arguments of the command sent by the user
	protected final String[] args;
	// TaskList instance from the current HelpBot instance.
	protected final TaskList taskList;
	// Ui instance from the current HelpBot instance.
	protected final Ui ui;

	/**
	 * Constructor for a Command instance.
	 *
	 * @param args Arguments of the command sent by the user.
	 * @param taskList TaskList instance from the current HelpBot instance.
	 * @param ui Ui instance from the current HelpBot instance.
	 */
	public Command(String[] args, TaskList taskList, Ui ui) {
		this.args = args;
		this.taskList = taskList;
		this.ui = ui;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Command command = (Command) o;
		return Arrays.equals(args, command.args) && Objects.equals(taskList, command.taskList) &&
				ui.equals(command.ui);
	}

	@Override
	public int hashCode() {
		int result = Objects.hash(taskList, ui);
		result = 31 * result + Arrays.hashCode(args);
		return result;
	}

	/**
	 * Runs the command.
	 *
	 * @throws HelpBotInvalidTaskTypeException If user tries to edit the time of a ToDo task.
	 * @throws HelpBotIllegalArgumentException If user specified arguments are invalid or missing.
	 */
	public abstract void run()
			throws HelpBotInvalidTaskTypeException, HelpBotIllegalArgumentException, HelpBotDateTimeFormatException;
}
