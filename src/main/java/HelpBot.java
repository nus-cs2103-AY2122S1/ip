import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HelpBot {
	// Command list
	private static final String EXIT = "bye";
	private static final String LIST = "list";

	// Constants
	private final String name;
	private final Scanner scanner = new Scanner(System.in);

	// Data storage
	private final List<String> tasks = new ArrayList<>();

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
		reply(message);
		listen();
	}

	/**
	 * Method called when player issues exit command on bot.
	 */
	private void onDisable() {
		reply("Good riddance...");
	}

	/**
	 * Method to listen for user inputs.
	 */
	private void listen() {
		while (scanner.hasNext()) {
			// Current message sent by the user
			String current = scanner.nextLine();
			if (current.equals(EXIT)) {
				break;
			}
			if (current.equals(LIST)) {
				listAllTasks();
				continue;
			}
			addTask(current);
		}
		onDisable();
	}

	/**
	 * Method to add a new task to the task list.
	 *
	 * @param task Task to be added to the task list.
	 */
	private void addTask(String task) {
		tasks.add(task);
		reply("Urgh, fine. Task '" + task + "' has been added.");
	}

	/**
	 * Method to list all added tasks in the task list.
	 */
	private void listAllTasks() {
		StringBuilder message = new StringBuilder("Oh. My. God. Ok... Here are your tasks");
		for (int i = 0; i < tasks.size(); ++i) {
			message.append("\n").append(i + 1).append(". ").append(tasks.get(i)).append(".");
		}
		reply(message.toString());
	}

	/**
	 * Method to print reply message correctly.
	 *
	 * @param message Message to be formatted.
	 */
	private void reply(String message) {
		System.out.println("____________________________________________________________");
		System.out.println(message);
		System.out.println("____________________________________________________________");
	}
}
