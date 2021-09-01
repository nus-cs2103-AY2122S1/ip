package duke.commands;

import duke.DukeExceptions;
import duke.Ui;
import duke.storage.Storage;

/**
 * Class to handle the done command.
 */
public class DoneCommand extends Command {
    private int index;

    /**
     * Constructor for a new DoneCommand instance with the specified index stored.
     *
     * @param input The index of the task to mark as done in the list.
     */

    public DoneCommand(int input) {
        this.index = input - 1;
    }

    /**
     * Mark the task in the index as done.
     * Prints out reply message and saves to file after.
     *
     * @param ui The Ui instance for printing
     * @param storage The Storage instance to get the task
     * @return A boolean of false to indicate the main while loop should not be broken
     */
    @Override
    public boolean execute(Ui ui, Storage storage) {
        try {
            storage.markAsFinished(index);
            ui.print("Well done! I have marked the following as finished: \n" + storage.getTask(index));
            storage.save();
        } catch (IndexOutOfBoundsException e) {
            ui.print("Oops, the list is not that big!");
        } catch (DukeExceptions e) {
            ui.printException(e);
        }
        return false;
    }
}
