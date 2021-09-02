package duke.commands;

import duke.gui.Ui;
import duke.tasks.TaskList;

/**
 * Encapsulates the information of a ListCommand object.
 */
public class ListCommand extends Command {

    @Override
    public String executeCommand(TaskList taskList) {
        return Ui.displayList(taskList);
    }
}
