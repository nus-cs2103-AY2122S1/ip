package kermit.command;

import kermit.KermitException;
import kermit.Storage;
import kermit.TaskList;
import kermit.Response;

/**
 * Exit command tells program to exit.
 */
public class ExitCommand extends Command {
    /**
     * Constructs ExitCommand.
     */
    public ExitCommand() {
    }

    /**
     * Executes exit command.
     * Notify user that program is exiting and saves the current state of the task list.
     *
     * @param taskList Instance of task list used.
     * @param response       Instance of Ui used.
     * @param storage  Instance of storage class used.
     * @throws KermitException if there is an error saving the file.
     */
    @Override
    public String execute(TaskList taskList, Response response, Storage storage) throws KermitException {
        storage.save(taskList);
        return response.getGoodbyeMessage();
    }

    /**
     * Returns if command is the exit command.
     *
     * @return Always returns true as this is the exit command.
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Returns syntax for command.
     *
     * @return Syntax for how command is used.
     */
    protected static String getSyntax() {
        return "bye";
    }
}
