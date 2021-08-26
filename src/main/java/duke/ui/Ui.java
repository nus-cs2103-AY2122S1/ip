package duke.ui;

/**
 * This is the Display class to handle UI of Duke.
 */
public class Ui {
	/**
	 * Prints welcome message.
	 */
	public static void showWelcomeMessage() {
		String welcomeString = "____________________________________________________________\n"
				+ "Yo! Duke here...on behalf of Yang Yuzhao.\n"
				+ "What do ya want from me?\n"
				+ "____________________________________________________________\n";
		System.out.println(welcomeString);
	}

	/**
	 * Prints bye message.
	 */
	public static void showByeMessage() {
		String byeString = "____________________________________________________________\n"
				+ "Duke out! Wake me up when ya need me again:)\n"
				+ "____________________________________________________________\n";
		System.out.println(byeString);
	}
}
