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
        assert splitInfo.length == 2 : "Event info is split wrongly";
        Event event = new Event(splitInfo[0].strip(), splitInfo[1]);
        return AddHandler.handleAdd(event, taskList, storage);
    }
}
