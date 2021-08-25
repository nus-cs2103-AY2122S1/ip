package duke.command;

import duke.util.Storage;
import duke.task.TaskList;
import duke.util.Ui;

/**
 * Lists all the task in the task list.
 */
public class ListCommand extends Command {
    /**
     * Retrieves all task in task list, attach task numbers and print to screen.
     *
     * @param storage storage instance initialised when duke is created.
     * @param taskList task list instance initialised when duke is created.
     * @param ui ui instance initialised when duke is created.
     */
    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) {
        ui.respond(String.format(
                        "Ooh yeah! Here are your %d tasks\n%s",
                        taskList.getSize(),
                        taskList.list())
        );
    }
}
