package duke.command;

import duke.FileManager;
import duke.Tasklist;
import duke.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(Tasklist tasks, Ui ui, FileManager fileManager) {
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
