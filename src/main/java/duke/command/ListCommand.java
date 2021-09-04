package duke.command;

import duke.util.TaskList;
import duke.util.Ui;
import duke.util.Storage;

import java.util.ArrayList;

/**
 * ListCommand class which handles the logic of listing tasks.
 */
public class ListCommand extends Command {
    public ListCommand() {}

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public ArrayList<String> execute(TaskList taskList, Ui ui, Storage storage) {
        return taskList.list();
    }
}
