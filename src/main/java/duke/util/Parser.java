package duke.util;

import duke.Duke;
import duke.exception.DukeException;

/**
 * Represents a parser for making sense of the user input.
 */
public class Parser {
	public static boolean parse(String command) throws DukeException {
		if (command.startsWith("bye")) {
			Ui.printFormattedMessage("Bye. Hope to see you again soon!\n");
			return true;
		} else if (command.startsWith("list")) {
			Duke.tasks.printTasks();
			return false;
		} else if (command.startsWith("done")) {
			Duke.tasks.handleDone(command);
		} else if (command.startsWith("delete")) {
			Duke.tasks.handleDelete(command);
		} else if (command.startsWith("todo")) {
			Duke.tasks.addToDo(command);
		} else if (command.startsWith("deadline")) {
			Duke.tasks.addDeadline(command);
		} else if (command.startsWith("event")) {
			Duke.tasks.addEvent(command);
		} else {
			throw new DukeException("I don't understand that command!\n");
		}

		Duke.storage.saveTasks(Duke.tasks.getTasks());
		return false;
	}
}
