package duke.ui;

import duke.operation.Task;

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

	/**
	 * Prints the message when a task is added.
	 *
	 * @param size current size of the storeroom
	 */
	public String printAddTask(int size, Task task) {
		String message = "Got it. I've added this task:\n  "
				+ task
				+ "\n"
				+ "Now you have "
				+ size
				+ " tasks in the list.";
		System.out.println("____________________________________________________________\n"
				+ message
				+ "\n"
				+ "____________________________________________________________\n");
		return message;
	}

	/**
	 * Prints task list.
	 */
	public void printList() {
		System.out.println("____________________________________________________________\n"
				+ "Here are the tasks in your list:");
		System.out.println("____________________________________________________________\n");
	}

	/**
	 * Prints the task that is marked done.
	 */
	public String printDoneTask(Task doneTask) {
		String message = "Nice! I've marked this task as done:\n  "
				+ doneTask;
		System.out.println("____________________________________________________________\n"
				+ message
				+ "\n"
				+ "____________________________________________________________\n");
		return message;
	}

	/**
	 * Prints the message when a task is deleted.
	 *
	 * @param size current size of the storeroom
	 */
	public String printDeleteTask(int size, Task task) {
		String message = "Noted. I've removed this task:\n  "
				+ task
				+ "\n"
				+ "Now you have "
				+ size
				+ " tasks in the list.";
		System.out.println("____________________________________________________________\n"
				+ message
				+ "\n"
				+ "____________________________________________________________\n");
		return message;

	}

	public String showLoadingErrorMessage() {
		return "Loading Error: Cannot load tasks from local storage.";
	}
}
