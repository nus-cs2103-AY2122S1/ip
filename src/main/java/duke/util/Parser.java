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
            return taskList -> taskList.printFullTaskList();
        } else if (command.contains("todo")) {
            return taskList -> taskList.addTodo(command);
        } else if (command.contains("event")) {
            return taskList -> taskList.addEvent(command);
        } else if (command.contains("deadline")) {
            return taskList -> taskList.addDeadline(command);
        } else if (command.contains("delete")) {
            return taskList -> taskList.deleteTask(command);
        } else if (command.contains("done")) {
            return taskList -> taskList.markTaskDone(command);
        } else if (command.contains("find")) {
            return taskList -> taskList.findFromList(command);
        } else {
            throw new UnknownCommandException();
        }
    }
}
