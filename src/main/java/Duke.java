import java.util.Scanner;

public class Duke {
	private final String HORIZONTAL_LINE = "\t____________________________________________________________\n";
	private final String MESSAGE_WELCOME = "Hi! I'm Sora. How can I help you?";
	private final String MESSAGE_EXIT = "Have a nice day! Good bye XD";
	
	public void run() {
		// Print welcome message
		printMessage(MESSAGE_WELCOME);
		
		// Setup scanner for user input
		Scanner sc = new Scanner(System.in);
		String input;
		
		while (!(input = sc.nextLine().trim()).equals("bye")) printMessage(input);
		
		// Close off scanner
		sc.close();
		
		// Print good bye message
		printMessage(MESSAGE_EXIT);
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
