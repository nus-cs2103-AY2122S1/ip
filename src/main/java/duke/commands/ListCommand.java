package duke.commands;

import duke.Ui;
import duke.tasks.TaskList;

/**
 * Encapsulates the information of a ListCommand object.
 */
public class ListCommand extends Command {

    @Override
    public void executeCommand(TaskList taskList) {
        Ui.displayList(taskList);
    }
}
