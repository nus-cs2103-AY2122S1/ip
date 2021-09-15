package duke.command;

import duke.exceptions.OutOfBoundException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

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
    public String delete() throws OutOfBoundException {
        int index = parser.getIndex(tasks.getSize());
        storage.deleteTask(tasks.get(index));
        return ui.showDelete(tasks.delete(index), String.valueOf(tasks.getSize()));
    }
}
