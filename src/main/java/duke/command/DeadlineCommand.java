package duke.command;

import java.io.IOException;
import java.time.DateTimeException;

import duke.DukeException;
import duke.Ui;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.tasklist.TaskList;

/**
 * DeadlineCommand is a duke.command which adds a deadline duke.task to the duke.task list.
 *
 * @author Zhen Xuan (Tutorial Group W12)
 * @version CS2103T AY21/22 S1
 */
public class DeadlineCommand extends Command {

    /**
     * Constructor.
     *
     * @param description it should contain the deadline description and the date
     */
    public DeadlineCommand(String description) {
        super(description);
    }

    /**
     * Adds the deadline into the duke.task list.
     *
     * @param tasks   the duke.task list
     * @param ui      the ui
     * @param storage the storage for the saved duke.task list
     * @throws DukeException if the date/time format is wrong or if the saved data is deleted midway /
     *                       the data file is missing by other factors
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            String[] deadlinePair = description.split("/by", 2);
            if (deadlinePair.length < 2 || deadlinePair[0].equals("") || deadlinePair[1].equals("")) {
                throw new DukeException("Your add deadline duke.command is incomplete.");
            }
            String desc = deadlinePair[0].trim();
            String date = deadlinePair[1].trim();
            tasks.addTask(new Deadline(false, desc, date));
            storage.add("D", desc, date);
        } catch (IOException e) {
            throw new DukeException("There is an error in adding the Deadline duke.task to your saved data.");
        } catch (DateTimeException e) {
            throw new DukeException("Please provide the time in YYYY-MM-DD HHMM format instead.");
        }
    }
}
