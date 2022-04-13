package nyx.command;

import nyx.NyxException;
import nyx.Storage;
import nyx.task.Event;
import nyx.task.TaskList;

/**
 * Represents a command to add an Event task.
 */
public class EventCommand extends Command {
    /**
     * Constructs an EventCommand object.
     *
     * @param information Information needed to add an Event task.
     */
    public EventCommand(String information) {
        super(information);
    }

    /**
     * Performs operations needed to add an Event task.
     *
     * @param taskList TaskList object containing all the tasks.
     * @param storage Storage object to deal with hard disk related operations.
     * @return String representation of the message for the user.
     * @throws NyxException If an error is encountered while adding the Event task.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws NyxException {
        String[] splitInfo = information.split(" /at ");
        if (splitInfo.length != 2) {
            throw new NyxException("Incorrect Event format! The correct format is { details } /at { datetime }");
        }
        Event event = new Event(splitInfo[0].strip(), splitInfo[1]);
        return AddHandler.handleAdd(event, taskList, storage);
    }

    public static void throwEmptyException() throws NyxException {
        throw new NyxException("The description of an event cannot be empty.");
    }
}
