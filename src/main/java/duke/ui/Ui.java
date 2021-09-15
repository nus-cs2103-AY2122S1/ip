package duke.ui;

/**
 * This is the Display class to handle UI of Duke.
 */
public class Ui {
	private final String WELCOME_STRING = "Yo! Duke here...on behalf of Yang Yuzhao.\n"
			+ "I can organise task for you!\n\n"
			+ "3 types of tasks are supported now:\n todo, deadline, event";
	private final String BYE_STRING = "Duke out! Wake me up when ya need me again:)";

	/**
	 * Prints welcome message.
	 */
	public String showWelcomeMessage() {
		System.out.println("____________________________________________________________\n"
				+ WELCOME_STRING
				+ "____________________________________________________________\n");
		return WELCOME_STRING;
	}

	/**
	 * Prints bye message.
	 */
	public String showByeMessage() {
		System.out.println("____________________________________________________________\n"
				+ BYE_STRING
				+ "____________________________________________________________\n");
		return BYE_STRING;
	}

}
