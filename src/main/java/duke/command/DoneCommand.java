package duke.command;

import java.io.IOException;

import duke.DukeException;
import duke.Ui;
import duke.storage.Storage;
import duke.tasklist.TaskList;

/**
 * DoneCommand is a duke.command which marks a specific Task as done from the TaskList.
 *
 * @author Zhen Xuan (Tutorial Group W12)
 * @version CS2103T AY21/22 S1
 */
public class DoneCommand extends Command {

    /**
     * Constructor.
     *
     * @param description it should contain index of the duke.task to be marked done
     */
    public DoneCommand(String description) {
        super(description);
    }

    /**
     * Marks the duke.task done from the duke.task list.
     *
     * @param tasks   the duke.task list
     * @param ui      the ui
     * @param storage the storage for the saved duke.task list
     * @throws DukeException if the index of the duke.task given is not found in the list or if the
     *                       saved data file is missing / corrupted midway during the running of Duke
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            int index = Integer.parseInt(description);
            if (index > tasks.count()) {
                throw new IllegalArgumentException();
            }
            tasks.setDone(index - 1);
            storage.setDone(index - 1);
        } catch (IllegalArgumentException e) {
            throw new DukeException("There is no such duke.task in existence.");
        } catch (IOException e) {
            throw new DukeException("There is an error reflecting the duke.task as done in the saved data.");
        }
    }
}
