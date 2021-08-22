package botto.command;

import botto.*;
import botto.task.Task;
import botto.task.Todo;
import botto.util.Storage;
import botto.util.TaskList;
import botto.util.Ui;

public class AddToDoCommand implements Command {

    private String command;

    public AddToDoCommand(String command) {
        this.command = command;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        String description;

        try {
            description = command.split(" ", 2)[1];
        } catch (Exception e) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }

        Task task = new Todo(description);
        taskList.addTask(task);

        ui.respondAdd(task, taskList.getSize());
        storage.save(taskList.getTasks());
    }
}
