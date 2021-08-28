package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

import java.io.IOException;

public class DeleteCommand extends Command {
    private int taskId;

    public DeleteCommand(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Task task = tasks.getTaskById(taskId);
            tasks.deleteTask(task);
            ui.showDeleteTask(tasks, task);
            storage.saveTasks(tasks);
        } catch (IOException e) {
            throw new DukeException("Ooooops something went wrong with the data file.");
        }
    }
}
