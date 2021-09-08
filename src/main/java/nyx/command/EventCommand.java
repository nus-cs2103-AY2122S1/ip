package nyx.command;

import nyx.NyxException;
import nyx.Storage;
import nyx.task.Event;
import nyx.task.TaskList;

public class EventCommand extends Command {
    public EventCommand(String information) {
        super(information);
    }

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
