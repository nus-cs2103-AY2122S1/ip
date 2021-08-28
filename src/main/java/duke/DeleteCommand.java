package duke;

import duke.DukeException.MissingTaskException;
import java.io.IOException;

public class DeleteCommand extends Command {
    private int taskId;

    public DeleteCommand(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Task task = tasks.getTaskFromId(taskId);
            tasks.deleteTask(task);
            ui.showDeleteTask(tasks, task);
            storage.saveTasks(tasks);
        } catch (IOException e) {
            throw new DukeException("Ooooops something went wrong with the data file.");
        }
    }
}
