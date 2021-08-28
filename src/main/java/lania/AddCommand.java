package lania;

import lania.task.Task;
import lania.task.TaskList;

import java.io.IOException;

public class AddCommand extends Command {

    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        tasks.update(task);
        try {
            storage.save(tasks);
        } catch (IOException e) {
            ui.showError();
        }
        ui.showUpdateMessage(tasks, task);
    }
}
