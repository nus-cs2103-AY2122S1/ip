package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class CommandList extends Command{
    public static final String KEYWORD = "list";

    public CommandList() {
    }

    @Override
    public void execute(TaskList tl, Storage st, Ui ui) {
        tl.printAllTasks();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
