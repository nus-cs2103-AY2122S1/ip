package duke.util;

import java.io.IOException;

import duke.exception.DukeException;
import duke.exception.UnknownCommandException;
import duke.task.TaskList;

/**
 * Represents a parser object that interprets commands.
 */
public class Parser {

    /**
     * Matches user input commands.
     * @param input the user input.
     * @throws DukeException Invalid command.
     * @throws IOException Invalid file.
     */
    public static ParseCommands parse(String input) throws DukeException, IOException {
        if (input.equals("list")) {
            return TaskList::printTasks;
        } else if (input.matches("^todo( .*)")) {
            return task -> task.addTodo(input);
        } else if (input.matches("^deadline( .*)")) {
            return task -> task.addDeadline(input);
        } else if (input.matches("^event( .*)")) {
            return task -> task.addEvent(input);
        } else if (input.matches("^done( .*)")) {
            return task -> task.finishTask(input);
        } else if (input.matches("^delete( .*)")) {
            return task -> task.deleteTask(input);
        } else if (input.matches("^find( .*)")) {
            return task -> task.find(input);
        } else if (input.matches("^undo( .*)")) {
            return task -> task.undo(input);
        } else {
            throw new UnknownCommandException();
        }
    }

}
