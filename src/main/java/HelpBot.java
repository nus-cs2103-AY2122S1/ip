import java.util.Scanner;

public class HelpBot {
	// Static fields
	private static final String EXIT = "bye";

	// Constants
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
		String message = "Hello! I'm \n" + name
				+ "\nHow may I serve your incredibly needy highness today?";
		reply(message);
		listen();
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
			reply(current);
		}
		onDisable();
	}

	/**
	 * Method called when player issues exit command on bot.
	 */
	private void onDisable() {
		reply("Good riddance...");
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
