package duke.commands;

import duke.Ui;
import duke.storage.Storage;

<<<<<<< HEAD
public class DeleteCommand extends Command {
    int index;

=======
/**
 * Class to handle the delete command.
 */
public class DeleteCommand extends Command {
    int index;

    /**
     * Public constructor for the DeleteCommand class
     *
     * @param input The index of the task to delete in the list
     * @return A new DeleteCommand instance with the specified index stored.
     */
>>>>>>> branch-A-JavaDoc
    public DeleteCommand(int input) {
        this.index = input - 1;
    }

    /**
     * Delete the task in the index.
     * Print out confirmation and Saves after.
     *
     * @param ui The Ui instance for printing messages
     * @param storage The Storage instance delete the task from
     * @return A boolean of false to indicate the main while loop should not be broken
     */
    @Override
    public boolean execute(Ui ui, Storage storage) {
        try {
            ui.print("Okay! I have deleted this task from your list: \n" + storage.getTask(index));
            storage.deleteFromList(index);
            storage.save();
        } catch (IndexOutOfBoundsException e) {
            ui.print("Oops, the list is not that big!");
        }
        return false;
    }
}
