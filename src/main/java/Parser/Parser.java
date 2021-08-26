package Parser;

import exception.DukeException;

public class Parser {
	/**
	 * Handles situation when input is invalid.
	 *
	 * @throws DukeException
	 */
	public static void invalidTask() throws DukeException {
		String message = "____________________________________________________________\n"
				+ "OOPS!!! I'm sorry, but I don't know what that means :-(\n"
				+ "____________________________________________________________\n";
		throw new DukeException(message);
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

}
