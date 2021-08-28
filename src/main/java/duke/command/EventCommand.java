package duke.command;

import java.io.IOException;
import java.time.DateTimeException;

import duke.DukeException;
import duke.Ui;
import duke.storage.Storage;
import duke.task.Event;
import duke.tasklist.TaskList;

/**
 * EventCommand is a duke.command which adds an event duke.task to the duke.task list.
 *
 * @author Zhen Xuan (Tutorial Group W12)
 * @version CS2103T AY21/22 S1
 */
public class EventCommand extends Command {

    /**
     * Constructor.
     *
     * @param description it should contain the event description and the date
     */
    public EventCommand(String description) {
        super(description);
    }

    /**
     * Adds the event into the duke.task list.
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
            String[] eventPair = description.split("/at", 2);
            if (eventPair.length < 2 || eventPair[0].equals("") || eventPair[1].equals("")) {
                throw new DukeException("Your add event duke.command is incomplete.");
            }
            String desc = eventPair[0].trim();
            String date = eventPair[1].trim();
            tasks.addTask(new Event(false, desc, date));
            storage.add("E", desc, date);
        } catch (IOException e) {
            throw new DukeException("There is an error in adding the Event duke.task to your saved data.");
        } catch (DateTimeException e) {
            throw new DukeException("Please provide the time in YYYY-MM-DD HHMM format instead.");
        }
    }
}
