package commands;

import java.io.IOException;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import tasks.Todo;

public class AddTodoCommand extends AddTaskCommand {

    public AddTodoCommand(String desc, boolean isDone) {
        super(desc, isDone);
    }

    @Override
    public String execute(Ui ui, TaskList taskList, Storage storage) {
        try {
            storage.writeTasksToFile(taskList, storage.getTaskFile());
            Todo newTodo = new Todo(this.desc, this.isDone);
            taskList.addTask(newTodo);
            return ui.getAddTaskResponse(newTodo);
        } catch (IOException e) {
            return ui.getFileWriteFailResponse(storage.getTaskFile());
        }

    }
}
