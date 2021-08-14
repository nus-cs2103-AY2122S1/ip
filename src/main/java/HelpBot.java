public class HelpBot {
	// Name of the HelpBot.
	private final String NAME;

	/**
	 * Constructor for a HelpBot instance.
	 *
	 * @param name Name of the bot to be created.
	 */
	public HelpBot(String name) {
		this.NAME = name;
		this.start();
	}

	/**
	 * Starts the operation of the bot.
	 */
	private void start() {
		System.out.println("____________________________________________________________");
		System.out.println("Hello! I'm \n" + NAME);
		System.out.println("What can I do for you?");
		System.out.println("____________________________________________________________");
	}
}
