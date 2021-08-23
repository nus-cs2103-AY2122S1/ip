package main.java.duke.command;

import main.java.duke.DukeException;
import main.java.duke.storage.Storage;
import main.java.duke.tasklist.TaskList;
import main.java.duke.Ui;

import java.io.IOException;
import java.time.DateTimeException;

/**
 * DoneCommand is a command which marks a specific Task as done from the TaskList.
 *
 * @author Zhen Xuan (Tutorial Group W12)
 * @version CS2103T AY21/22 S2
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
     * @param ui      the ui
     * @param storage the storage for the saved task list
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            int index = Integer.parseInt(description);
            if (index > tasks.count()) {
                throw new IllegalArgumentException();
            }
            tasks.setDone(index - 1);
            storage.setDone(index - 1);
        } catch (DateTimeException e) {
            throw new DukeException("Your date (YYYY-MM-DD) / date & time (YYYY-MM-DD HHMM) (24h) " +
                    "is given in the wrong format!");
        } catch (IllegalArgumentException e) {
            throw new DukeException("There is no such task in existence.");
        }  catch (IOException e) {
            throw new DukeException("There is an error reflecting the task as done in the saved data.");
        }
    }
}
