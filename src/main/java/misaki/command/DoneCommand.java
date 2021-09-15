package misaki.command;

import misaki.exceptions.MisakiException;
import misaki.exceptions.OutOfBoundException;
import misaki.parser.Parser;
import misaki.storage.Storage;
import misaki.tasklist.TaskList;
import misaki.ui.Ui;

/**
 * Represents a command class that marks a task as done.
 *
 * @author Chen Hsiao Ting
 * @version CS2103T AY21/22 Semester 1
 */
public class DoneCommand extends Command {

    /**
     * A constructor for DoneCommand.
     *
     * @param tasks   A list of current Tasks.
     * @param parser  Parser to interpret user input.
     * @param storage Storage to store data
     * @param ui      Ui responsible for user interaction.
     */
    public DoneCommand(TaskList tasks, Parser parser, Storage storage, Ui ui) {
        super(tasks, parser, storage, ui);
    }

    /**
     * Marks a task as done.
     *
     * @return String representation of the done task.
     * @throws OutOfBoundException If user enter an invalid index.
     */
    public String done() throws MisakiException {
        int index = parser.getIndex(tasks.getSize());
        String doneMsg = tasks.done(index);
        storage.saveFile(tasks.toStorageString());
        return doneMsg;
    }
}
