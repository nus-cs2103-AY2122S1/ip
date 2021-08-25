package duke.command;

import duke.FileManager;
import duke.Tasklist;
import duke.UI;

public class ListCommand extends Command {
    @Override
    public void execute(Tasklist tasks, UI ui, FileManager fileManager) {
        ui.printList(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String toString() {
        return "list command";
    }
}
