package duke.commands;

import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;

/**
 * Class that is a subclass of Command class
 * and handles the behaviour of the Command for returning
 * a list of the Tasks currently in the TaskList
 */
public class ListCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        for (int i = 0; i < taskList.numberOfTasks(); i++) {
            ui.printResponse((i + 1) + "." + taskList.getTask(i).toString());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
