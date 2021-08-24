package dino.command;

import dino.exception.EmptyListException;
import dino.util.Storage;
import dino.task.TaskList;

public class ListCommand extends Command {
    @Override
    public void execute(Storage storage, TaskList taskList) throws EmptyListException {
        taskList.printTaskList();
    }
}
