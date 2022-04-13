package duke.commands;

import duke.DukeExceptions;
import duke.gui.Ui;
import duke.storage.Storage;

/**
 * Class to handle the done command.
 */
public class DoneCommand extends Command {
    private final int index;

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
     * @param ui The Ui instance for printing.
     * @param storage The Storage instance to get the task.
     * @return String to represent the reply of Duke.
     */
    @Override
    public String execute(Ui ui, Storage storage) {
        try {
            storage.markAsFinished(index);
            storage.save();
            String dukeReply = "Well done! I have marked the following as finished: \n"
                    + storage.getTask(index);
            return ui.reply(dukeReply);
        } catch (IndexOutOfBoundsException e) {
            return ui.reply("Oops, the list is not that big!");
        } catch (DukeExceptions e) {
            return ui.printException(e);
        }
    }
}
