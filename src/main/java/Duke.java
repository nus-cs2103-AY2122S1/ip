import java.util.Scanner;

public class Duke {
	private final String HORIZONTAL_LINE = "\t____________________________________________________________\n";
	private final String MESSAGE_WELCOME = "Hi! I'm Sora. How can I help you?";
	private final String MESSAGE_EXIT = "Have a nice day! Good bye XD";
	
	private final String[] listTasks = new String[100];
	private int numTasks = 0;
	
	/**
	 * Main body of the bot.
	 */
	public void run() {
		// Print welcome message
		printMessage(MESSAGE_WELCOME);
		
		// Setup scanner for user input
		Scanner sc = new Scanner(System.in);
		String input;
		
		// Main body of chat box, each if else handles one type of command
		while (!(input = sc.nextLine().trim()).equals("bye")) {
			if (input.equals("list")) printFullList();
			else addTask(input);
		}
		
		// Close off scanner
		sc.close();
		
		// Print good bye message
		printMessage(MESSAGE_EXIT);
	}
	
	/**
	 * Adds a new task to the list of tasks.
	 *
	 * @param description new task to be added
	 */
	private void addTask(String description) {
		// Add the newly created task into list of tasks
		listTasks[numTasks++] = description;
		
		// Display message
		printMessage("added: " + description);
	}
	
	/**
	 * Prints all the tasks in the list.
	 */
	private void printFullList() {
		// Reformat the list of tasks
		StringBuilder msg = new StringBuilder();
		
		for (int i = 0; i < numTasks - 1; i++) msg.append(i + 1).append(". ").append(listTasks[i]).append("\n");
		msg.append(numTasks).append(". ").append(listTasks[numTasks - 1]);
		
		// Display message
		printMessage(msg.toString());
	}
	
	/**
	 * Prints a horizontal line, followed by the message on a newline, then another horizontal line on a newline.
	 * Each newline will be prepended with a tab.
	 * <p></p>
	 * It looks like the following:
	 * <br>
	 * -----------
	 * <br>
	 * message
	 * <br>
	 * -----------
	 *
	 * @param msg message to be displayed
	 */
	private void printMessage(String msg) {
		String format = HORIZONTAL_LINE +
				"\t%s\n" +
				HORIZONTAL_LINE;
		System.out.printf(format, msg.replaceAll("\n", "\n\t"));
	}
	
	public static void main(String[] args) {
		new Duke().run();
	}
}
