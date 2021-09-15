package duke.parser;

import duke.exception.DukeException;

public class Parser {
	protected String NOT_UNDERSTOOD_MESSAGE = "OOPS!!! I'm sorry, but I don't know what that means :-(";

	public String getNotUnderstoodMessage() {
		return NOT_UNDERSTOOD_MESSAGE;
	}

	/**
	 * Handles situation when input is invalid.
	 *
	 * @throws DukeException
	 */
	public void invalidTask() throws DukeException {
		throw new DukeException(NOT_UNDERSTOOD_MESSAGE);
	}

	/**
	 * Checks if the first word of a input string is valid.
	 *
	 * @param input            input string
	 * @param expectedTaskName expected name of a task
	 */
	public void checkIfFirstWordValid(String input, String expectedTaskName) {
		String firstWord = input.split(" ", 2)[0];
		try {
			if (!firstWord.equals(expectedTaskName)) {
				throw new DukeException(NOT_UNDERSTOOD_MESSAGE);
			}
		} catch (DukeException e) {
			System.out.println(e);
		}
	}

	/**
	 * Returns whether the description of a task is empty.
	 *
	 * @param input input string
	 * @return boolean value of whether the description is empty
	 */
	public boolean isDescriptionEmpty(String input) {
		String removedSpace = input.replaceAll("\\s", "");
		return removedSpace.equals(input);
	}

	public void firstWordAndDescriptionCheck(String input, String expectedTaskName) throws DukeException {
		checkIfFirstWordValid(input, expectedTaskName);
		if (isDescriptionEmpty(input)) {
			throw new DukeException(NOT_UNDERSTOOD_MESSAGE);
		}
	}
}
