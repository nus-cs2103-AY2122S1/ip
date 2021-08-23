package main.java.duke.command;

import main.java.duke.DukeException;
import main.java.duke.storage.Storage;
import main.java.duke.tasklist.TaskList;
import main.java.duke.Ui;
import main.java.duke.task.Event;

import java.io.IOException;
import java.time.DateTimeException;

/**
 * EventCommand is a command which adds an event task to the task list.
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
     * Adds the event into the task list.
     *
     * @param tasks   the task list
     * @param ui      the ui
     * @param storage the storage for the saved task list
     * @throws DukeException if the date/time format is wrong or if the saved data is deleted midway /
     *                       the data file is missing by other factors
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            String[] eventPair = description.split("/at", 2);
            if (eventPair.length < 2 || eventPair[0].equals("") || eventPair[1].equals("")) {
                throw new DukeException("Your add event command is incomplete.");
            }
            String desc = eventPair[0].trim();
            String date = eventPair[1].trim();
            tasks.addTask(new Event(false, desc, date));
            storage.add("E", desc, date);
        } catch (IOException e) {
            throw new DukeException("There is an error in adding the Event task to your saved data.");
        } catch (DateTimeException e) {
            throw new DukeException("Please provide the time in YYYY-MM-DD HHMM format instead.");
        }
    }
}
