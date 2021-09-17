package duke.command;

import java.io.IOException;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.utils.DukeException;

/**
 * DoneCommand is a command which marks a specific Task as done from the TaskList.
 *
 * @author Zhen Xuan (Tutorial Group W12)
 * @version CS2103T AY21/22 S1
 */
public class DoneCommand extends Command {

    /**
     * Constructor.
     *
     * @param description it should contain index of the task to be marked done
     */
    public DoneCommand(String description) {
        super(description);
    }

    /**
     * Marks the task done from the task list.
     *
     * @param tasks   the task list
     * @param storage the storage for the saved task list
     * @throws DukeException if the index of the task given is not found in the list or if the
     *                       saved data file is missing / corrupted midway during the running of Duke
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        try {
            int index = Integer.parseInt(DESCRIPTION);
            if (index > tasks.count()) {
                throw new IllegalArgumentException();
            }
            storage.setDone(index - 1);
            return tasks.setDone(index - 1);
        } catch (IllegalArgumentException e) {
            throw new DukeException("There is no such task in existence.");
        } catch (IOException e) {
            throw new DukeException("There is an error reflecting the task as done in the saved data.");
        }
    }
}
