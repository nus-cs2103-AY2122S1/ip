package duke.ui;

/**
 * This is the Display class to handle UI of Duke.
 */
public class Ui {
	private String welcomeString = "Yo! Duke here...on behalf of Yang Yuzhao.\n"
			+ "What do ya want from me?";
	private String byeString = "Duke out! Wake me up when ya need me again:)";

	/**
	 * Prints welcome message.
	 */
	public String showWelcomeMessage() {
		System.out.println("____________________________________________________________\n"
				+ welcomeString
				+ "____________________________________________________________\n");
		return welcomeString;
	}

	/**
	 * Prints bye message.
	 */
	public String showByeMessage() {
		System.out.println("____________________________________________________________\n"
				+ byeString
				+ "____________________________________________________________\n");
		return byeString;
	}

}
