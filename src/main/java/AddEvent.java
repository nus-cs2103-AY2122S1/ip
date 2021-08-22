package main.java;

import java.io.IOException;
import java.time.DateTimeException;

/**
 * AddEvent is a command which adds an event task to the task list.
 *
 * @author Zhen Xuan (Tutorial Group W12)
 * @version CS2103T AY21/22 S2
 */
public class AddEvent extends Command {

    /**
     * Constructor.
     *
     * @param description it should contain the event description and the date
     */
    AddEvent(String description) {
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
            if (eventPair[0] == "" || eventPair[1] == "") {
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
