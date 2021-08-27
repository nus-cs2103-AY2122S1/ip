package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.Ui;

import java.time.DateTimeException;

/**
 * ListCommand is a duke.command which prints out all the Tasks in the list, and even
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
     * @param tasks   the duke.task list
     * @param ui      the ui
     * @param storage the storage for the saved duke.task list
     * @throws DukeException if the date/time format is wrong
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String date = super.description.replace(" ", "");
        try {
            if (date.equals("")) {
                tasks.printList();
            } else {
                tasks.printListDate(date);
            }
        } catch (DateTimeException e) {
            throw new DukeException("Your date (YYYY-MM-DD) / date & time (YYYY-MM-DD HHMM) (24h) " +
                    "is given in the wrong format!");
        }
    }
}
