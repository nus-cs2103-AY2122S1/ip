package duke.command;

import java.time.DateTimeException;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.utils.DukeException;

/**
 * ListCommand is a command which prints out all the Tasks in the list, and even
 * prints only tasks of a specific date if the date is specified.
 *
 * @author Zhen Xuan (Tutorial Group W12)
 * @version CS2103T AY21/22 S1
 */
public class ListCommand extends Command {

    /**
     * Constructor.
     *
     * @param description it is either empty, or contains a date for which tasks with that date should be returned.
     */
    public ListCommand(String description) {
        super(description);
    }

    /**
     * Lists down all the tasks (of the specified date).
     *
     * @param tasks   the task list
     * @param storage the storage for the saved task list
     * @throws DukeException if the date/time format is wrong
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        String date = DESCRIPTION.replace(" ", "");
        try {
            if (date.equals("")) {
                return tasks.printList();
            } else {
                return tasks.printListDate(date);
            }
        } catch (DateTimeException e) {
            throw new DukeException("Your date (YYYY-MM-DD) / date & time (YYYY-MM-DD HHMM) (24h) "
                    + "is given in the wrong format!");
        }
    }
}
