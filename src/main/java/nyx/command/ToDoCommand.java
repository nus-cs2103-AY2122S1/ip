package nyx.command;

import nyx.NyxException;
import nyx.Storage;
import nyx.task.TaskList;
import nyx.task.ToDo;

public class ToDoCommand extends Command {
    public ToDoCommand(String information) {
        super(information);
    }

    @Override
    public String execute(TaskList taskList, Storage storage) throws NyxException {
        ToDo toDo = new ToDo(information);
        return AddHandler.handleAdd(toDo, taskList, storage);
    }
}
