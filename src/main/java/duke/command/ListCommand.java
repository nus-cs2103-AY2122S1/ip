package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * This class represents the command when the user types "list" validly.
 */
public class ListCommand extends Command {
    /**
     * Lists out the tasks in the task list.
     *
     * @param tasks task list
     * @param storage storage
     * @param ui ui
     * @return output for this command.
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) {
        return tasks.listOut();
    }


}
