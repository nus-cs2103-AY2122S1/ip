package duke.commands;

import duke.Tasklist;
import duke.UI;
import duke.PersistentStorage;

public class ListAllCommand extends Command {

    public ListAllCommand(){}

    public void executeCommand(Tasklist taskList, UI ui, PersistentStorage storage) {
        ui.listAllTasks(taskList);
    }
}
