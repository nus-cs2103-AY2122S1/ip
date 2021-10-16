package duke.parser;

import duke.exception.DukeException;
import duke.operation.Deadline;
import duke.operation.Event;
import duke.operation.Task;
import duke.operation.ToDo;
import duke.tool.TaskList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;

public class Parser {
	private TaskList tl = new TaskList();
	protected String NOT_UNDERSTOOD_MESSAGE = "OOPS!!! I'm sorry, but I don't know what that means :-(";
	protected String INVALID_COMMAND_PREFIX = "The input command prefix is not supported."
			+ "See User Guide for supported command prefix.";
	protected String EMPTY_DESCRIPTION = "The description of the task is empty.";
	protected String MISSING_BY = "OOPS!!! Missing separator '/by' for deadline input.";
	protected String MISSING_AT = "OOPS!!! Missing separator '/at' for event input.";
	protected String DATETIME_FORMAT_ERROR = "Incorrect date-time format/range. Use yyyy-MM-dd HH:mm.";

	public String getNotUnderstoodMessage() {
		return NOT_UNDERSTOOD_MESSAGE;
	}

	/**
	 * Handles situation when input is invalid.
	 *
	 * @throws DukeException
	 */
	public void invalidTask() throws DukeException {
		throw new DukeException(INVALID_COMMAND_PREFIX);
	}

	/**
	 * Checks if the first word of an input string is valid.
	 *
	 * @param input            input string
	 * @param expectedTaskName expected name of a task
	 */
	public void checkIfFirstWordValid(String input, String expectedTaskName) {
		String firstWord = input.split(" ", 2)[0];
		try {
			if (!firstWord.equals(expectedTaskName)) {
				throw new DukeException(INVALID_COMMAND_PREFIX);
			}
		} catch (DukeException e) {
			System.out.println(e);
		}
	}

	/**
	 * Returns whether the description of a task is empty.
	 */
	public void firstWordAndDescriptionCheck(String input, String expectedTaskName) throws DukeException {
		checkIfFirstWordValid(input, expectedTaskName);
		if (input.trim().equals(expectedTaskName)) {
			throw new DukeException(EMPTY_DESCRIPTION);
		}
	}

	/**
	 * Returns task list as String.
	 */
	public String parseTaskListAsString(TaskList tl) {
		ArrayList<Task> taskList = tl.getTaskList();
		Collections.sort(taskList);
		String message = "Here are the tasks in your list:\n";
		int counter = 1;
		for (Task taskForLoop : taskList) {
			message += counter
					+ "."
					+ taskForLoop
					+ "\n";
			counter++;
		}
		return message;
	}

	/**
	 * Returns filtered task list.
	 */
	public String parseFilteredTaskListAsString(TaskList inputTaskList) {
		ArrayList<Task> taskList = inputTaskList.getTaskList();
		String output = "Here are the tasks found by your keyword in your list:\n";
		int counter = 1;
		for (Task taskForLoop : taskList) {
			output += counter
					+ "."
					+ taskForLoop
					+ "\n";
			counter++;
		}
		return output;
	}

	public ToDo splitToDO(String input) {
		assert input.length() >= 5 : "OOPS!!! todo task input does not have enough length.";
		return new ToDo(input.substring(5).trim(), false);
	}


	/**
	 * Splits deadline input string into body and deadline date.
	 *
	 * @param input input string
	 * @return new split Deadline object
	 */
	public Deadline splitDeadline(String input) throws DukeException {
		LocalDateTime by;
		if (!input.contains("/by")) {
			throw new DukeException(MISSING_BY);
		}
		String[] partsOfDeadline = input.split("/by ");
		String deadlineContent = partsOfDeadline[0].substring(9).trim();
		try {
			DateTimeFormatter dateTimeFormatterFrom = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
			by = LocalDateTime.parse(partsOfDeadline[1], dateTimeFormatterFrom);
		} catch (DateTimeParseException e) {
			throw new DukeException(DATETIME_FORMAT_ERROR);
		}
		assert partsOfDeadline.length == 2 : "OOPS!!! Missing deadline date.";
		return new Deadline(deadlineContent, by, false);
	}

	/**
	 * Splits event string into body and event date.
	 *
	 * @param input input string
	 * @return new split event object
	 */
	public Event splitEvent(String input) throws DukeException {
		LocalDateTime at;
		if (!input.contains("/at")) {
			throw new DukeException(MISSING_AT);
		}
		String[] partsOfEvent = input.split("/at ");
		String eventContent = partsOfEvent[0].substring(6).trim();
		try {
			DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
			assert partsOfEvent.length == 2 : "OOPS!!! Missing event date.";
			at = LocalDateTime.parse(partsOfEvent[1], dateTimeFormatter);
		} catch (DateTimeParseException e) {
			throw new DukeException(DATETIME_FORMAT_ERROR);
		}
		return new Event(eventContent, at, false);
	}
}
