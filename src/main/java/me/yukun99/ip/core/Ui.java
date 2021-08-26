package me.yukun99.ip.core;

import me.yukun99.ip.tasks.Task;

import java.io.IOException;

/**
 * Class used to handle message the Ui and responses from the HelpBot.
 */
public class Ui {
	// Name of the HelpBot.
	private final String name;
	// List of tasks to be completed.
	private final TaskList taskList;
	// Storage instance from the current HelpBot.
	private final Storage storage;
	// TaskFinder instance from the current HelpBot.
	private final TaskFinder taskFinder;

	// Field placeholders.
	private static final String NAME_PLACEHOLDER = "%name%";
	private static final String TASKNUM_PLACEHOLDER = "%tasks%";

	// Reply header/footer.
	private static final String REPLY_HEADER = "===================To Your Royal Highness===================";
	private static final String REPLY_FOOTER = "============================================================";

	// Line separator.
	private static final String NEW_LINE = System.lineSeparator();

	// Command help prefix.
	private static final String CMD_PREFIX = "  > ";

	// Command usage descriptions.
	private static final String HELP_LIST = "view all your tasks, optionally (troublesome for me) by date";
	private static final String HELP_FIND = "finds all tasks containing specified keyword (please no)";
	private static final String HELP_TODO = "add a simple todo task";
	private static final String HELP_DEADLINE = "add a task to be done by specified date/time";
	private static final String HELP_EVENT = "add a task that happens at specified date/time";
	private static final String HELP_DATE = "     - date format: yyyy-mm-dd";
	private static final String HELP_TIME = "     - time format: hh:mm:ss";
	private static final String HELP_UPDATE = "modify the date/time of task at specified index";
	private static final String HELP_DELETE = "delete a task (LIFT MY BURDEN!)";
	private static final String HELP_EXIT = "(please for the love of God) let me rest! :)";

	// Message sent to user when bot starts.
	private static final String ENABLE = "Beep... You've reached the voicemail of HelpBot inc."
			+ "Please speak after the dial tone."
			+ NEW_LINE + "Still here? Sigh... thought that would work. My name is"
			+ NEW_LINE + NAME_PLACEHOLDER
			+ NEW_LINE + "Here is the myriad of ways you can inconvenience me:"
			+ NEW_LINE + "  [] denotes optional arguments, () denotes REQUIRED arguments."
			+ NEW_LINE + CMD_PREFIX + "'list [date]' - " + HELP_LIST
			+ NEW_LINE + CMD_PREFIX + "'find (keyword)' - " + HELP_FIND
			+ NEW_LINE + CMD_PREFIX + "'todo (task)' - " + HELP_TODO
			+ NEW_LINE + CMD_PREFIX + "'deadline (task) /by (date) (time)' - " + HELP_DEADLINE
			+ NEW_LINE + CMD_PREFIX + "'event (task) /at (date) (time)' - " + HELP_EVENT
			+ NEW_LINE + CMD_PREFIX + "'update (task index) /to (date) (time)' - " + HELP_UPDATE
			+ NEW_LINE + HELP_DATE
			+ NEW_LINE + HELP_TIME
			+ NEW_LINE + CMD_PREFIX + "'delete (task index)' - " + HELP_DELETE
			+ NEW_LINE + CMD_PREFIX + "'bye' - " + HELP_EXIT;

	// Message sent to user when user requests for help.
	private static final String HELP = "You've ALREADY forgotten the commands? How???"
			+ NEW_LINE + "Actually, why am I even surprised... here are the commands."
			+ NEW_LINE + CMD_PREFIX + "'list [date]' - " + HELP_LIST
			+ NEW_LINE + CMD_PREFIX + "'find (keyword)' - " + HELP_FIND
			+ NEW_LINE + CMD_PREFIX + "'todo (task)' - " + HELP_TODO
			+ NEW_LINE + CMD_PREFIX + "'deadline (task) (date/time)' - " + HELP_DEADLINE
			+ NEW_LINE + CMD_PREFIX + "'event (task) (date/time)' - " + HELP_EVENT
			+ NEW_LINE + CMD_PREFIX + "'update (task index) (date/time)' - " + HELP_UPDATE
			+ NEW_LINE + HELP_DATE
			+ NEW_LINE + HELP_TIME
			+ NEW_LINE + CMD_PREFIX + "'delete (task index)' - " + HELP_DELETE
			+ NEW_LINE + CMD_PREFIX + "'bye' - " + HELP_EXIT;

	// Message sent to user when user lists all tasks.
	private static final String LIST = "Oh. My. God. Fine. Here are your tasks:";
	// Message sent to user when user lists all tasks by date.
	private static final String LIST_DATE = "Do you even realise how hard it was to do this?";
	// Message sent to user when user finds all tasks by keyword.
	private static final String FIND = "Why must you make life hard for me?";
	// Message sent to user when user adds new task.
	private static final String ADD = "Urgh, fine. Your task has been added:";
	// Message sent to user when user deletes finished task.
	private static final String DELETE_DONE = "Can't you just keep track of this yourself? Fine, removed this for " +
			"you:";
	// Message sent to user when user deletes unfinished task.
	private static final String DELETE_UNDONE = "Oh, procrastinating now are we? Sure, removed this:";
	// Message sent to user when user exits HelpBot.
	private static final String EXIT = "Good riddance.";
	// Message sent to user when user marks an undone task as done.
	private static final String DONE_UNCOMPLETED =
			"About time you did your work, you lazy bum! I GUESS I'll mark it as done for you:";
	// Message sent to user when user marks a completed task as done.
	private static final String DONE_COMPLETED =
			"You've already done this idiot! I'm watching Twitch, stop bothering me!";
	// Message sent to user to tell user how many tasks are left in the TaskList.
	private static final String REMAINING = "You now have " + TASKNUM_PLACEHOLDER + " tasks to do.";
	// Message sent to user when user updates a task's timing.
	private static final String UPDATE = "Dude, make up your mind! I'll update it, but just this once, okay?";

	/**
	 * Constructor for a Ui instance.
	 *
	 * @param name Name of the HelpBot.
	 */
	public Ui(String name, TaskList taskList, Storage storage, TaskFinder taskFinder) {
		this.name = name.replace("\n", NEW_LINE);
		this.taskList = taskList;
		this.storage = storage;
		this.taskFinder = taskFinder;
	}

	/**
	 * Sends user instructions and bot information on startup.
	 */
	public void start() {
		sendMessage(ENABLE.replace(NAME_PLACEHOLDER, name));
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
		sendMessage(LIST + taskList.toString().replace("\n", NEW_LINE));
	}

	/**
	 * Sends user a list of all tasks happening at the specified date in the DateTimePair.
	 *
	 * @param pair Date/time pair to send tasks to user for.
	 */
	public void listByDate(DateTimePair pair) {
		sendMessage(LIST_DATE + taskList.listByDate(pair).replace("\n", NEW_LINE));
	}

	/**
	 * Sends user a list of all tasks containing the specified word.
	 *
	 * @param word Word that has to be in all returned tasks.
	 */
	public void findByWord(String word) {
		sendMessage(FIND + taskFinder.findTasksByWord(word).replace("\n", NEW_LINE));
	}

	/**
	 * Sends user a message telling them task has been added.
	 *
	 * @param task Task that has been added.
	 */
	public void add(Task task) {
		sendMessage(ADD
				+ NEW_LINE + task
				+ NEW_LINE + remaining());
	}

	/**
	 * Sends user a message telling them task is already done.
	 *
	 * @param task Task that has already been done.
	 */
	public void alreadyDone(Task task) {
		sendMessage(DONE_COMPLETED
				+ NEW_LINE + task);
	}

	/**
	 * Sends user a message telling them a task has been marked as done.
	 *
	 * @param task Task that has been marked as done.
	 */
	public void done(Task task) {
		sendMessage(DONE_UNCOMPLETED
				+ NEW_LINE + task);
	}

	/**
	 * Gets message containing information on number of tasks in the list.
	 *
	 * @return Message containing information on number of tasks in the list.
	 */
	private String remaining() {
		return REMAINING.replace(TASKNUM_PLACEHOLDER, taskList.getRemaining() + "");
	}

	/**
	 * Sends user a message telling them a task has been updated.
	 *
	 * @param task Task that has been updated.
	 */
	public void update(Task task) {
		sendMessage(UPDATE + NEW_LINE + task);
	}

	/**
	 * Sends user a message telling them a task has been deleted.
	 *
	 * @param task Task that has been deleted.
	 * @param done Whether deleted task has been done.
	 */
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

	/**
	 * Sends user error message.
	 *
	 * @param e Error to be included in the message.
	 */
	public void error(Exception e) {
		sendMessage(e.toString());
	}

	/**
	 * Sends formatted messages to the user.
	 *
	 * @param message Message to be formatted.
	 */
	private void sendMessage(String message) {
		String reply = REPLY_HEADER
				+ NEW_LINE + message
				+ NEW_LINE + REPLY_FOOTER
				+ NEW_LINE;
		System.out.print(reply);
		try {
			storage.saveMessage(reply);
		} catch (IOException ignored) {}
	}
}
