package duke;

import duke.command.Command;
import duke.command.CommandKeyword;
import duke.exception.DukeException;
import duke.exception.InvalidCommandException;
import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;

public class Parser {

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
            default:
                /* will never be executed because the error would have been caught in run() method
                   if the user input a command that is invalid */
        }
        return shouldContinue;
    }

    public static int stringToInt(String description) throws InvalidCommandException {
        if (description.matches("\\d+")) {
            return Integer.parseInt(description);
        } else {
            throw new InvalidCommandException();
        }
    }
}
