package duke.util;

import duke.Duke;
import duke.TaskList;
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
	 * @throws DukeException if the user input is not a valid command
	 */
	public static String parse(String command) throws DukeException {
		String response;
		if (command.startsWith("bye")) {
			return "Bye. Hope to see you again soon!\n";
		} else if (command.startsWith("list")) {
			return TaskList.printTasks("");
		} else if (command.startsWith("find")) {
			response = TaskList.findTasks(command);
		} else if (command.startsWith("done")) {
			response = TaskList.handleDone(command);
		} else if (command.startsWith("delete")) {
			response = TaskList.handleDelete(command);
		} else if (command.startsWith("todo")) {
			response = TaskList.addToDo(command);
		} else if (command.startsWith("deadline")) {
			response = TaskList.addDeadline(command);
		} else if (command.startsWith("event")) {
			response = TaskList.addEvent(command);
		} else {
			return "I don't understand that command!\n";
		}

		Duke.getStorage().saveTasks(TaskList.getTasks());
		return response;
	}
}
