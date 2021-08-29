package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.util.ArrayList;

public class DisplayListCommand extends Command{
    public DisplayListCommand() {}

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public ArrayList<String> execute(TaskList taskList, Ui ui, Storage storage) {
        return taskList.displayList();
    }
}
