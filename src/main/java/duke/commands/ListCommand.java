package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import duke.exceptions.InvalidDirectoryException;

public class ListCommand extends Command {

    public ListCommand() {

    }

    public void execute(TaskList task, Ui ui, Storage storage) throws InvalidDirectoryException {
        try {
            ui.printList(task);
        } catch (NullPointerException npe) {
            throw new InvalidDirectoryException("   Unable to retrieve task info as "
                    + "specified directory does not exist");
        }
    }

    public boolean isExit() {
        return false;
    }
}
