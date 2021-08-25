package duke.command;

import duke.FileManager;
import duke.Tasklist;
import duke.UI;

/**
 * A command which aims to list the current tasks.
 */
public class ListCommand extends Command {
    /**
     * Lists the current tasks.
     *
     * @param tasks current list of task.
     * @param ui the ui to interact with the user.
     * @param fileManager the filemanger that manages the storage of duke.
     */
    @Override
    public void execute(Tasklist tasks, UI ui, FileManager fileManager) {
        ui.printList(tasks);
    }

    /**
     * Returns if the function is a exit command.
     *
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String toString() {
        return "list command";
    }
}
