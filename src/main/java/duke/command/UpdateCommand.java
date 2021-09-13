package duke.command;

import duke.Storage;
import duke.task.TaskList;

public abstract class UpdateCommand extends Command {

    public static final String COMMAND_WORD = "update";

    /**
     * Executes the command to update the description and/or date and time of the task.
     *
     * @param tasks The user's task list.
     * @param storage The storage object used to save data to the data file.
     * @return A message informing the user that the task has been updated
     * successfully.
     */
    @Override
    public abstract String execute(TaskList tasks, Storage storage);

}
