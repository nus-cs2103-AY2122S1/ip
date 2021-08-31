package duke.util;

import duke.exception.UnknownCommandException;
import duke.task.TaskList;

/**
 * Interpreter for user's command.
 *
 * @author Zhang Shi Chen
 */
public class Parser {
    /**
     * Interprets the command entered by user and returns the corresponding method
     * wrapped in a CheckedFunction.
     *
     * @param command command to be interpreted
     * @return a CheckedFunction containing the method
     * @throws UnknownCommandException if user inputs invalid command
     */
    public static CheckedFunction interpretCommand(String command) throws UnknownCommandException {
        // Each if else handles one type of command
        if (command.equals("list")) {
            return TaskList::printFullList;
        } else if (command.matches("^done( .*)?")) {
            return (tasks) -> tasks.taskDone(command);
        } else if (command.matches("^todo( .*)?")) {
            return (tasks) -> tasks.addTodo(command);
        } else if (command.matches("^deadline( .*)?")) {
            return (tasks) -> tasks.addDeadline(command);
        } else if (command.matches("^event( .*)?")) {
            return (tasks) -> tasks.addEvent(command);
        } else if (command.matches("^delete( .*)?")) {
            return (tasks) -> tasks.taskDelete(command);
        } else if (command.matches("^find( .*)?")) {
            return (tasks) -> tasks.findInList(command);
        } else {
            throw new UnknownCommandException();
        }
    }
}
