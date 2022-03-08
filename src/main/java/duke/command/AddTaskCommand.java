package duke.command;

import java.io.IOException;

import duke.Message;
import duke.TaskList;
import duke.storage.TaskStorage;
import duke.task.Task;
import duke.ui.Ui;

public class AddTaskCommand extends Command {
    private Task task;

    public AddTaskCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, TaskStorage taskStorage)
            throws IOException {
        taskList.addTask(task);
        taskStorage.addTaskToStorage(task);

        String addTaskMessage = Message.getAddTaskMessage(task, taskList.getSize());
        ui.setCurrentMessage(addTaskMessage);
    }

}
