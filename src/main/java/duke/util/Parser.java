package duke.util;

import duke.exception.DukeException;
import duke.exception.UnknownCommandException;
import duke.task.TaskList;
import java.io.IOException;

public class Parser {
    /**
     * Matches user input commands. 
     *
     * @param input the user input.
     * @throws DukeException Invalid command.
     * @throws IOException File
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
        } else {
            throw new UnknownCommandException();
        }
    }
}
