package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.util.ArrayList;

public class DisplayListCommand extends Command{
    private final boolean isList;

    public DisplayListCommand(boolean isList) {
        this.isList = isList;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public ArrayList<String> execute(TaskList taskList, Ui ui, Storage storage) {
        return (isList)
                ? taskList.displayList()
                : taskList.displayArchive();
    }
}
