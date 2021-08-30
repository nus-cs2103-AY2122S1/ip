package lania.command;

import lania.Storage;
import lania.Ui;
import lania.task.TaskList;

import java.io.IOException;

public class DeleteCommand extends Command {

    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        ui.showRemoveMessage(tasks, tasks.remove(index));
        try {
            storage.save(tasks);
        } catch (IOException e) {
            ui.showError();
        }
    }
}
