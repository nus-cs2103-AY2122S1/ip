package duke.command;

import duke.exceptions.OutOfBoundException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

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
    public String done() throws OutOfBoundException {
        int index = parser.getIndex(tasks.getSize());
        storage.updateTask(tasks.get(index));
        return tasks.done(index);
    }
}
