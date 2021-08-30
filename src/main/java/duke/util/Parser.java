package duke.util;

import duke.Duke;
import duke.exception.DukeException;

/**
 * Represents a parser for making sense of the user input.
 */
public class Parser {

	/**
	 * Determines whether Parser should continue parsing based on the user input.
	 * 
	 * @param command the user input
	 * @return true if Parser should continue parsing, false otherwise
	 * @throws DukeException if the user input is invalid
	 */
	public static String parse(String command) throws DukeException {
		if (command.startsWith("bye")) {
			return "Bye. Hope to see you again soon!\n";
		} else if (command.startsWith("list")) {
			return Duke.tasks.printTasks("");
		} else if (command.startsWith("find")) {
			return Duke.tasks.findTasks(command);
		} else if (command.startsWith("done")) {
			return Duke.tasks.handleDone(command);
		} else if (command.startsWith("delete")) {
			return Duke.tasks.handleDelete(command);
		} else if (command.startsWith("todo")) {
			return Duke.tasks.addToDo(command);
		} else if (command.startsWith("deadline")) {
			return Duke.tasks.addDeadline(command);
		} else if (command.startsWith("event")) {
			return Duke.tasks.addEvent(command);
		} else {
			throw new DukeException("I don't understand that command!\n");
		}
	}
}
