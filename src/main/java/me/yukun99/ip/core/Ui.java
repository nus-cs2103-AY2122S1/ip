package me.yukun99.ip.core;

import me.yukun99.ip.tasks.Task;

/**
 * Text Ui of the HelpBot.
 */
public class Ui {
	// Name of the HelpBot.
	private final String name;
	private final TaskList taskList;

	// Name placeholder
	private static final String NAME_PLACEHOLDER = "%name%";

	// Reply header/footer.
	private static final String REPLY_HEADER = "===================To Your Royal Highness===================";
	private static final String REPLY_FOOTER = "============================================================";

	// Line separator.
	private static final String NEW_LINE = System.lineSeparator();

	// Command echo prefix.
	private static final String CMD_PREFIX = "  > ";

	// Command descriptions.
	private static final String HELP_LIST = "view all your tasks";
	private static final String HELP_TODO = "add a simple todo task";
	private static final String HELP_DEADLINE = "add a task to be done by specified date/time";
	private static final String HELP_EVENT = "add a task that happens at specified date/time";
	private static final String HELP_UPDATE = "modify the date/time of task at specified index";
	private static final String HELP_DELETE = "delete a task";
	private static final String HELP_EXIT = "(please for the love of God) let me rest! :)";

	// Message sent to user when bot starts.
	private static final String ENABLE = "Oh great, you again... Name's"
			+ NEW_LINE + NAME_PLACEHOLDER
			+ NEW_LINE + "Here is the myriad of ways you can inconvenience me:"
			+ NEW_LINE + CMD_PREFIX + "'list' - " + HELP_LIST
			+ NEW_LINE + CMD_PREFIX + "'todo (task)' - " + HELP_TODO
			+ NEW_LINE + CMD_PREFIX + "'deadline (task) /by (date/time)' - " + HELP_DEADLINE
			+ NEW_LINE + CMD_PREFIX + "'event (task) /at (date/time)' - " + HELP_EVENT
			+ NEW_LINE + CMD_PREFIX + "'update (task index) /to (date/time)' - " + HELP_UPDATE
			+ NEW_LINE + CMD_PREFIX + "'delete (task index)' - " + HELP_DELETE
			+ NEW_LINE + CMD_PREFIX + "'bye' - " + HELP_EXIT;

	// Message sent to user when user requests for help.
	private static final String HELP = "You've ALREADY forgotten the commands? How???"
			+ NEW_LINE + "Actually, why am I even surprised... here are the commands."
			+ NEW_LINE + CMD_PREFIX + "'list' - " + HELP_LIST
			+ NEW_LINE + CMD_PREFIX + "'todo (task)' - " + HELP_TODO
			+ NEW_LINE + CMD_PREFIX + "'deadline (task) (date/time)' - " + HELP_DEADLINE
			+ NEW_LINE + CMD_PREFIX + "'event (task) (date/time)' - " + HELP_EVENT
			+ NEW_LINE + CMD_PREFIX + "'update (task index) (date/time)' - " + HELP_UPDATE
			+ NEW_LINE + CMD_PREFIX + "'delete (task index)' - " + HELP_DELETE
			+ NEW_LINE + CMD_PREFIX + "'bye' - " + HELP_EXIT;

	// Message sent to user when user deletes finished task.
	private static final String DELETE_DONE = "Can't you just keep track of this yourself? Fine, removed this for " +
			"you:";
	// Message sent to user when user deletes unfinished task.
	private static final String DELETE_UNDONE = "Oh, procrastinating now are we? Sure, removed this:";
	// Message sent to user when user exits HelpBot.
	private static final String EXIT = "Good riddance...";

	/**
	 * Constructor for a Ui instance.
	 *
	 * @param name Name of the HelpBot.
	 */
	public Ui(String name, TaskList taskList) {
		this.name = name;
		this.taskList = taskList;
	}

	/**
	 * Sends user instructions and bot information on startup.
	 */
	public void start() {
		sendMessage(ENABLE.replace(NAME_PLACEHOLDER, this.name));
	}

	/**
	 * Sends user instructions when requested.
	 */
	public void help() {
		sendMessage(HELP);
	}

	/**
	 * Sends user a list of all tasks.
	 */
	public void list() {
		sendMessage(this.taskList.toString());
	}

	public static void alreadyDone(Task task) {
		sendMessage("You've already done this task, stop bothering me!"
				+ NEW_LINE + task);
	}

	public static void done(Task task) {
		sendMessage("About time you did your work, you lazy bum! I GUESS I'll mark it as done for you:"
				+ NEW_LINE + task);
	}

	private String remaining() {
		return "You now have " + this.taskList.getRemaining() + " tasks to do.";
	}

	public void update(Task task) {
		String reply = "Dude, make up your mind! I'll update it, but just this once, okay?";
		reply += NEW_LINE + task;
		sendMessage(reply);
	}

	public void delete(Task task, boolean done) {
		String reply;
		if (done) {
			reply = DELETE_DONE;
		} else {
			reply = DELETE_UNDONE;
		}
		reply += NEW_LINE + task
				+ NEW_LINE + remaining();
		sendMessage(reply);
	}

	/**
	 * Sends user exit message.
	 */
	public void exit() {
		sendMessage(EXIT);
	}

	public void error(Exception e) {
		sendMessage(e.toString());
	}

	/**
	 * Sends formatted messages to the user.
	 *
	 * @param message Message to be formatted.
	 */
	private static void sendMessage(String message) {
		System.out.println(REPLY_HEADER);
		System.out.println(message);
		System.out.println(REPLY_FOOTER);
	}
}
