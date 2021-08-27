package me.yukun99.ip;

import me.yukun99.ip.commands.Command;
import me.yukun99.ip.core.Parser;
import me.yukun99.ip.core.Storage;
import me.yukun99.ip.core.TaskFinder;
import me.yukun99.ip.core.TaskList;
import me.yukun99.ip.core.Ui;
import me.yukun99.ip.exceptions.HelpBotDateTimeFormatException;
import me.yukun99.ip.exceptions.HelpBotIllegalArgumentException;
import me.yukun99.ip.exceptions.HelpBotInvalidCommandException;
import me.yukun99.ip.exceptions.HelpBotInvalidTaskTypeException;

import java.io.IOException;
import java.util.Scanner;

/**
 * Simple task tracking chat bot.
 */
public class HelpBot {
	// Scanner to scan for text inputs.
	private final Scanner scanner;
	// Ui instance to handle text Ui.
	private final Ui ui;
	// Parser instance to parse user commands.
	private final Parser parser;
	// Storage instance to handle file IO.
	private final Storage storage;

	// Whether to exit bot.
	private boolean exit = false;

	/**
	 * Constructor for a HelpBot instance.
	 *
	 * @param name Name of the bot to be created.
	 * @param filepath Filepath of the input file.
	 */
	public HelpBot(String name, String filepath) throws IOException {
		TaskFinder taskFinder = new TaskFinder();
		TaskList taskList = new TaskList(taskFinder);
		this.storage = new Storage(filepath, taskList);
		this.storage.loadTasks();
		this.ui = new Ui(name, taskList, storage, taskFinder);
		this.scanner = storage.getInputs();
		this.parser = new Parser(this, this.scanner, taskList, this.ui, this.storage);
		this.ui.start();
		this.listen();
	}

	/**
	 * Method to listen for user inputs.
	 */
	private void listen() {
		while (scanner.hasNext()) {
			try {
				Command command = parser.parseCommand();
				command.run();
			} catch (HelpBotIllegalArgumentException | HelpBotInvalidCommandException
					| HelpBotInvalidTaskTypeException | HelpBotDateTimeFormatException e) {
				ui.error(e);
			}
			if (exit) {
				return;
			}
		}
	}

	/**
	 * Method to exit the bot.
	 */
	public void exit() {
		exit = true;
		scanner.close();
		ui.exit();
	}
}
