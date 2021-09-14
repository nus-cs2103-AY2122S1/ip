package duke.commands;

import duke.gui.Ui;
import duke.storage.Storage;
import duke.tasks.Task;


/**
 * Class to handle the delete command.
 */
public class DeleteCommand extends Command {
    private final int index;

    /**
     * Construct a new DeleteCommand instance with the specified index stored.
     *
     * @param input The index of the task to delete in the list.
     */

    public DeleteCommand(int input) {
        this.index = input - 1;
    }

    /**
     * Deletes the task in the index.
     * Print out confirmation and Saves after.
     *
     * @param ui The Ui instance for printing messages.
     * @param storage The Storage instance delete the task from.
     * @return String to represent the reply of Duke.
     */
    @Override
    public String execute(Ui ui, Storage storage) {
        try {
            Task deletedTask = storage.getTask(index);
            storage.deleteFromList(index);
            storage.save();
            String dukeReply = "Okay! I have deleted this task from your list: \n"
                    + deletedTask.toString();
            return ui.reply(dukeReply);
        } catch (IndexOutOfBoundsException e) {
            return ui.reply("Oops, the list is not that big!");
        }
    }
}
