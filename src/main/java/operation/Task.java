package operation;

import exception.DukeException;

/**
 * This is the Task class to handle all tasks.
 */
public class Task {
	protected String description;
	protected boolean isDone;

	/**
	 * Constructor for Task objects.
	 * @param description input string
	 */
	public Task(String description) {
		this.description = description;
		this.isDone = false;
	}

	/**
	 * Gets status icon of a task for printing purpose.
	 * @return
	 */
	public String getStatusIcon() {
		return (isDone ? "[X]" : "[ ]"); // mark done task with X
	}

	/**
	 * Marks a task as done.
	 */
	public void doneTask() {
		this.isDone = true;
	}

	/**
	 * Checks if the first word of a input string is valid.
	 * @param input input string
	 * @param expectedTaskName expected name of a task
	 */
	public static void checkIfFirstWordValid(String input, String expectedTaskName) {
		String firstWord = input.split(" ", 2)[0];
		try {
			if (!firstWord.equals(expectedTaskName)) {
				String message = "____________________________________________________________\n"
						+ "OOPS!!! I'm sorry, but I don't know what that means :-(\n"
						+ "____________________________________________________________\n";
				throw new DukeException(message);
			}
		} catch (DukeException e) {
			System.out.println(e);
		}
	}

	/**
	 * Returns whether the description of a task is empty.
	 * @param input input string
	 * @return boolean value of whether the description is empty
	 */
	public static boolean isDescriptionEmpty(String input) {
		String removedSpace = input.replaceAll("\\s", "");
		return removedSpace.equals(input);
	}

	/**
	 * Prints the task that is marked done.
	 */
	public void printDoneTask() {
		System.out.println("____________________________________________________________\n"
				+ "Nice! I've marked this task as done:\n  "
				+ this
				+ "\n"
				+ "____________________________________________________________\n");
	}


	@Override
	public String toString() {
		return this.getStatusIcon() + " " + this.description;
	}

	/**
	 * Prints the message when a task is added.
	 * @param size current size of the storeroom
	 */
	public void printAddTask(int size) {
		System.out.println("____________________________________________________________\n"
				+ "Got it. I've added this task:\n  "
				+ this
				+ "\n"
				+ "Now you have "
				+ size
				+ " tasks in the list."
				+ "\n"
				+ "____________________________________________________________\n");
	}

	/**
	 * Prints the message when a task is deleted.
	 * @param size current size of the storeroom
	 */
	public void printDeleteTask(int size) {
		System.out.println("____________________________________________________________\n"
				+ "Noted. I've removed this task:\n  "
				+ this
				+ "\n"
				+ "Now you have "
				+ size
				+ " tasks in the list."
				+ "\n"
				+ "____________________________________________________________\n");
	}
}