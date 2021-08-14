import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HelpBot {
	// Command list
	private enum Command {
		TASK,
		EXIT,
		LIST,
		DONE,
	}

	// Constants/Variables related to the instance
	private final String name;
	private final Scanner scanner = new Scanner(System.in);
	private final List<Task> tasks = new ArrayList<>();

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
		String message = "Hello! I'm \n" + name
				+ "\nHow may I serve your incredibly needy highness today?";
		HelpBot.reply(message);
		listen();
	}

	/**
	 * Method called when player issues exit command on bot.
	 */
	private void onDisable() {
		HelpBot.reply("Good riddance...");
	}

	/**
	 * Method to listen for user inputs.
	 */
	private void listen() {
		while (scanner.hasNext()) {
			// Current message sent by the user
			String current = scanner.nextLine();
			if (runCommand(current)) {
				return;
			}
		}
	}

	/**
	 * Method to add a new task to the task list.
	 *
	 * @param task Task to be added to the task list.
	 */
	private void addTask(String task) {
		Task added = new Task(task);
		tasks.add(added);
		HelpBot.reply("Urgh, fine. Task '" + task + "' has been added.");
	}

	/**
	 * Tries to get a task from the current message and mark it as done.
	 *
	 * @param current Current message sent by the user.
	 */
	private void setDone(String current) {
		String task = current.replace("done ", "");
		setDone(Integer.parseInt(task));
	}

	/**
	 * Tries to mark the task at the specified number in the list as done.
	 *
	 * @param number Number of the task in the list to be marked.
	 */
	private void setDone(int number) {
		try {
			tasks.get(number - 1).setDone();
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Hello? Can you COUNT? You don't HAVE this many tasks!!!");
		}
	}

	/**
	 * Method to list all added tasks in the task list.
	 */
	private void listAllTasks() {
		StringBuilder message = new StringBuilder("Oh. My. God. Fine. Here are your tasks:");
		for (int i = 0; i < tasks.size(); ++i) {
			message.append("\n ").append(i + 1).append(".").append(tasks.get(i));
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
	private boolean runCommand(String message) {
		if (message.startsWith("done")) {
			String task = message.replace("done ", "");
			if (Methods.isInt(task)) {
				setDone(message);
				return false;
			}
		}
		switch (message) {
		case "list":
			listAllTasks();
			return false;
		case "bye":
			onDisable();
			return true;
		}
		addTask(message);
		return false;
	}
}
