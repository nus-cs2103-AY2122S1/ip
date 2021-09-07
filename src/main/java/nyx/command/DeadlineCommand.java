package nyx.command;

import nyx.NyxException;
import nyx.Storage;
import nyx.task.Deadline;
import nyx.task.TaskList;

public class DeadlineCommand extends Command {
    public DeadlineCommand(String information) {
        super(information);
    }

    @Override
    public String execute(TaskList taskList, Storage storage) throws NyxException {
        String[] splitInfo = information.split(" /by ");
        Deadline deadline = new Deadline(splitInfo[0].strip(), splitInfo[1]);
        return AddHandler.handleAdd(deadline, taskList, storage);
    }
}
