package main.java.duke.command;

import main.java.duke.DukeException;
import main.java.duke.Storage;
import main.java.duke.TaskList;
import main.java.duke.Ui;
import main.java.duke.task.Event;

import java.io.IOException;
import java.time.DateTimeException;

/**
 * EventCommand is a command which adds an event task to the task list.
 *
 * @author Zhen Xuan (Tutorial Group W12)
 * @version CS2103T AY21/22 S2
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
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            String[] eventPair = description.split("/at", 2);
            if (eventPair.length < 2 || eventPair[0].equals("") || eventPair[1].equals("")) {
                throw new DukeException("Your add event command is incomplete.");
            }
            tasks.addTask(new Event(false, eventPair[0], eventPair[1]));
            storage.add("E", eventPair[0], eventPair[1]);
        } catch (IOException e) {
            throw new DukeException("There is an error in adding the Event task to your saved data.");
        } catch (DateTimeException e) {
            throw new DukeException("Please provide the time in YYYY-MM-DD HHMM format instead.");
        }
    }
}
