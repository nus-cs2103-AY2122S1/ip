package duke.util;

import duke.exceptions.UnknownCommandException;

/**
 * Encapsulates Parser of Duke bot.
 */
public class Parser {
    /**
     * Parses user input command and returns command to run in duke bot.
     *
     * @param command user input command
     * @return Command to run in duke bot.
     * @throws UnknownCommandException invalid user command
     */
    public static Command parse(String command) throws UnknownCommandException {
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