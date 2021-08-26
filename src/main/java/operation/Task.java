package operation;

import exception.DukeException;

public class Task {
	protected String description;
	protected boolean isDone;

	public Task(String description) {
		this.description = description;
		this.isDone = false;
	}

	public String getStatusIcon() {
		return (isDone ? "[X]" : "[ ]"); // mark done task with X
	}

	public void doneTask() {
		this.isDone = true;
	}

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

	public static boolean isDescriptionEmpty(String input) {
		String removedSpace = input.replaceAll("\\s", "");
		return removedSpace.equals(input);
	}

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

	public void addTask(int size) {
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