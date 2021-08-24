package duke.util;

import duke.exceptions.DukeException;
import duke.exceptions.UnknownCommandException;

/**
 * Encapsulates Parser of Duke bot.
 */
public class Parser {
    public static Command parse(String command) throws DukeException {
        if (command.contains("list")) {
            return tasks -> tasks.printTaskList();
        } else if (command.contains("todo")) {
            return tasks -> tasks.addTodo(command);
        } else if (command.contains("event")) {
            return tasks -> tasks.addEvent(command);
        } else if (command.contains("deadline")) {
            return tasks -> tasks.addDeadline(command);
        } else if (command.contains("delete")) {
            return tasks -> tasks.deleteTask(command);
        } else if (command.contains("done")) {
            return tasks -> tasks.markTaskDone(command);
        } else {
            throw new UnknownCommandException();
        }
    }
}