package me.yukun99.ip;

// Exceptions

import me.yukun99.ip.exceptions.HelpBotIllegalArgumentException;
import me.yukun99.ip.exceptions.HelpBotIllegalCommandException;
import me.yukun99.ip.exceptions.HelpBotInvalidTaskException;
import me.yukun99.ip.exceptions.HelpBotInvalidTaskTypeException;

import me.yukun99.ip.tasks.Deadline;
import me.yukun99.ip.tasks.Event;
import me.yukun99.ip.tasks.Task;
import me.yukun99.ip.tasks.ToDo;

import java.util.Scanner;

public class HelpBot {
	// Constants/Variables related to the instance
	private final String name;
	private final Scanner scanner = new Scanner(System.in);

	/**
	 * Constructor for a HelpBot instance.
	 *
	 * @param name Name of the bot to be created.
	 */
	public HelpBot(String name) {
		this.name = name;
		this.onEnable();
	}

	/**
	 * Method called when bot is instantiated.
	 */
	private void onEnable() {
		onEnableMessage();
		listen();
	}

	/**
	 * Method called when player issues exit command on bot.
	 */
	private void onDisable() {
		HelpBot.reply("Good riddance...");
	}

	private void onEnableMessage() {
		String message = "Oh great, you again! I'm \n"
				+ name
				+ "\nHere is the myriad of ways you can inconvenience me:"
				+ "\n  > 'list' - view all your tasks"
				+ "\n  > 'todo (task)' - add a simple todo task"
				+ "\n  > 'deadline (task) /by (date/time)' - add a task with a deadline"
				+ "\n  > 'event (task) /at (date/time)' - add a task with a specified date/time"
				+ "\n  > 'update (index) /to (date/time)' - change a task to happen at a new date/time"
				+ "\n  > 'bye' - (please for the love of God) let me rest! :)";
		HelpBot.reply(message);
	}

	/**
	 * Method to listen for user inputs.
	 */
	private void listen() {
		while (scanner.hasNext()) {
			// Current message sent by the user
			String current = scanner.nextLine();
			try {
				if (runCommand(current)) {
					return;
				}
			} catch (HelpBotIllegalArgumentException e) {
				HelpBot.reply("You didn't give me a task to add!\n" + e);
			} catch (HelpBotIllegalCommandException e) {
				HelpBot.reply("I literally TOLD you what you can do!\n" + e);
			}
		}
	}

	/**
	 * Method to add a new task to the task list.
	 *
	 * @param task Task to be added to the task list.
	 * @param type Type of task to be added to the task list.
	 */
	private void addTask(String[] task, Task.TaskType type) throws IllegalStateException {
		Task added;
		switch (type) {
		case TODO:
			added = new ToDo(task[0]);
			break;
		case EVENT:
			added = new Event(task[0], task[1]);
			break;
		case DEADLINE:
			added = new Deadline(task[0], task[1]);
			break;
		default:
			throw new IllegalStateException("Unexpected value: " + type);
		}
		Task.tasks.add(added);
		String reply = "Urgh, fine. Your task has been added:\n"
				+ added + "\n"
				+ getTaskAmount();
		HelpBot.reply(reply);
	}

	/**
	 * Tries to get a task from the current message and mark it as done.
	 *
	 * @param current Current message sent by the user.
	 */
	private void setDone(String current) throws HelpBotInvalidTaskException {
		String task = current.replace("done ", "");
		try {
			setDone(Integer.parseInt(task));
		} catch (NumberFormatException e) {
			HelpBot.reply("Hello? Do you know what a number is?");
			throw new HelpBotInvalidTaskException(e, "done", task);
		}
	}

	/**
	 * Tries to mark the task at the specified number in the list as done.
	 *
	 * @param number Number of the task in the list to be marked.
	 */
	private void setDone(int number) {
		try {
			Task.tasks.get(number - 1).setDone();
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Hello? Can you COUNT? You don't HAVE this many tasks!!!");
		}
	}

	/**
	 * Gets the message to reply for the current number of tasks.
	 *
	 * @return Message to reply for the current number of tasks.
	 */
	private String getTaskAmount() {
		return "You now have " + Task.tasks.size() + " tasks to do.";
	}

	/**
	 * Method to list all added tasks in the task list.
	 */
	private void listAllTasks() {
		StringBuilder message = new StringBuilder("Oh. My. God. Fine. Here are your tasks:");
		for (int i = 0; i < Task.tasks.size(); ++i) {
			message.append("\n ").append(i + 1).append(".").append(Task.tasks.get(i));
		}
		HelpBot.reply(message.toString());
	}

	/**
	 * Method to print reply message correctly.
	 *
	 * @param message Message to be formatted.
	 */
	public static void reply(String message) {
		System.out.println("____________________________________________________________");
		System.out.println(message);
		System.out.println("____________________________________________________________");
	}

	/**
	 * Gets command type from message sent by the user.
	 *
	 * @param message Message sent by the user.
	 * @return Whether to exit the program.
	 */
	private boolean runCommand(String message) throws HelpBotIllegalArgumentException, HelpBotIllegalCommandException {
		// Mark task as done command
		if (message.startsWith("done ")) {
			try {
				setDone(message);
			} catch (HelpBotInvalidTaskException e) {
				HelpBot.reply("You do realise this isn't a task, right?\n" + e);
			}
			return false;
		}
		// Update date of deadline or event tasks
		if (message.startsWith("update ")) {
			if (message.contains(" /to ") && !message.endsWith(" /to ") && !message.startsWith(" /to ")) {
				try {
					Task.updateTask(message.replace("update ", ""));
				} catch (HelpBotInvalidTaskTypeException e) {
					HelpBot.reply("You can't do this, dumbo.\n" + e);
				} catch (HelpBotInvalidTaskException e) {
					HelpBot.reply("Hello, do you know what a number is?\n" + e);
				}
				return false;
			}
		}
		// Add new task command
		if (message.startsWith("todo ") || message.startsWith("event ") || message.startsWith("deadline ")) {
			Task.TaskType type = getTaskType(message);
			message = message.replace(type.toString().toLowerCase() + " ", "");
			if (message.startsWith("/by ") || message.startsWith("/at ")) {
				throw new HelpBotIllegalArgumentException(null);
			}
			try {
				parseTask(message, type);
			} catch (HelpBotIllegalArgumentException e) {
				HelpBot.reply("You didn't specify the time, dummy!\n" + e);
			}
			return false;
		}
		switch (message) {
		// List all tasks command
		case "list":
			listAllTasks();
			return false;
		// End bot usage command
		case "bye":
			onDisable();
			return true;
		}
		throw new HelpBotIllegalCommandException(message);
	}

	private Task.TaskType getTaskType(String message) {
		return Task.TaskType.valueOf(message.split(" ")[0].toUpperCase());
	}

	/**
	 * Parses the message sent by the user for a task to be added.
	 *
	 * @param message Message sent by the user.
	 */
	private void parseTask(String message, Task.TaskType type) throws HelpBotIllegalArgumentException {
		String[] result = new String[1];
		switch (type) {
		case TODO:
			result[0] = message.replace("todo ", "");
			break;
		case EVENT:
			result = message
					.replace("event ", "")
					.split(" /at ");
			if (result.length < 2) {
				throw new HelpBotIllegalArgumentException(null);
			}
			break;
		case DEADLINE:
			result = message
					.replace("deadline ", "")
					.split(" /by ");
			if (result.length < 2) {
				throw new HelpBotIllegalArgumentException(null);
			}
			break;
		}
		addTask(result, type);
	}
}
