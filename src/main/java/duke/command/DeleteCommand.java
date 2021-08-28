package duke.command;

import duke.data.TaskHandler;
import duke.data.exception.DukeException;

import duke.storage.Storage;

/**
 * Class that encapsulates the "Delete" Command.
 *
 * @author Won Ye Ji
 */
public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "DELETE";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + "Deletes selected task from list.";

    /**
     * Constructor for DeleteCommand.
     *
     * @param th Task Handler that handles the operation.
     * @param str Storage that holds the task information.
     */
    public DeleteCommand(TaskHandler th, Storage str) {
        super(th, str);
    }

    /**
     * Executes the "Delete" Command.
     *
     * @param cmd Command string to be executed.
     */
    @Override
    public void execute(String cmd) {
        try {
            taskHandler.deleteTask(Integer.parseInt(cmd.substring(7)));
            taskHandler.printNoOfTasks();
            storage.updateFile(taskHandler.formatTasksToSave());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }
}