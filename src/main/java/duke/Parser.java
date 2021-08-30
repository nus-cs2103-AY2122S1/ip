package duke;

import java.util.ArrayList;

import duke.command.Command;
import duke.command.CommandKeyword;
import duke.exception.DukeException;
import duke.exception.InvalidCommandException;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Deals with making sense of the user command and executing it.
 */
public class Parser {

    /**
     * Execute user command and returns a boolean that represents the continuation of the program.
     * Does not check for validity of user command.
     *
     * @param command Command inputted by user.
     * @param tl List of tasks.
     * @param storage Storage that store tasks.
     * @return A boolean to determine if the program should continue.
     * @throws DukeException If the command execution is unsuccessful.
     */
    public static boolean parse(Command command, TaskList tl, Storage storage) throws DukeException {
        boolean shouldContinue = true;
        CommandKeyword keyword = command.getKeyword();
        String restOfCommand = command.getRestOfCommand();
        switch (keyword) {
        case TODO:
        case DEADLINE:
        case EVENT:
            ArrayList<Task> tasksAfterAdd = tl.addTask(restOfCommand, keyword);
            if (tasksAfterAdd != null) {
                storage.save(tasksAfterAdd);
            }
            break;
        case LIST:
            if (restOfCommand.equals("")) {
                tl.display();
                break;
            } else {
                throw new InvalidCommandException();
            }
        case DONE:
            int indexToMark = Parser.stringToInt(restOfCommand) - 1;
            ArrayList<Task> tasksAfterDone = tl.markTask(indexToMark);
            if (tasksAfterDone != null) {
                storage.save(tasksAfterDone);
            }
            break;
        case DELETE:
            int indexToDelete = Parser.stringToInt(restOfCommand) - 1;
            ArrayList<Task> tasksAfterDelete = tl.deleteTask(indexToDelete);
            if (tasksAfterDelete != null) {
                storage.save(tasksAfterDelete);
            }
            break;
        case BYE:
            if (restOfCommand.equals("")) {
                shouldContinue = false;
                break;
            } else {
                throw new InvalidCommandException();
            }
        case FIND:
            tl.find(restOfCommand);
            break;
        default:
            /* will never be executed because the error would have been caught in run() method
               if the user input a command that is invalid */
        }
        return shouldContinue;
    }

    /**
     * Convert a type String of an integer to type int.
     * Returns the integer that is converted.
     *
     * @param description The string that is to be converted.
     * @return The converted integer.
     * @throws InvalidCommandException If description does not represent a type String of an integer.
     */
    public static int stringToInt(String description) throws InvalidCommandException {
        if (description.matches("\\d+")) {
            return Integer.parseInt(description);
        } else {
            throw new InvalidCommandException();
        }
    }
}
