package misaki.command;

import misaki.exceptions.MisakiException;
import misaki.exceptions.OutOfBoundException;
import misaki.parser.Parser;
import misaki.storage.Storage;
import misaki.tasklist.TaskList;
import misaki.ui.Ui;

/**
 * Represents a command class that deletes a task.
 *
 * @author Chen Hsiao Ting
 * @version CS2103T AY21/22 Semester 1
 */
public class DeleteCommand extends Command {

    /**
     * A constructor for DeleteCommand.
     *
     * @param tasks   A list of current Tasks.
     * @param parser  Parser to interpret user input.
     * @param storage Storage to store data
     * @param ui      Ui responsible for user interaction.
     */
    public DeleteCommand(TaskList tasks, Parser parser, Storage storage, Ui ui) {
        super(tasks, parser, storage, ui);
    }

    /**
     * Deletes a task from the current list of Tasks.
     *
     * @return String representation of the deleted task.
     * @throws OutOfBoundException If user enter an invalid index.
     */
    public String delete() throws MisakiException {
        int index = parser.getIndex(tasks.getSize());
        String deleteMsg = tasks.delete(index);
        storage.saveFile(tasks.toStorageString());
        return ui.showDelete(deleteMsg, String.valueOf(tasks.getSize()));
    }
}
