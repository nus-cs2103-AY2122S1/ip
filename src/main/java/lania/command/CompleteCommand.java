package lania.command;

import lania.Storage;
import lania.Ui;
import lania.task.TaskList;

import java.io.IOException;

public class CompleteCommand extends Command {

    private int index;

    public CompleteCommand(int index) {
        this.index = index;
    }

    public void execute (TaskList tasks, Storage storage, Ui ui) {
        tasks.complete(index);
        try {
            storage.save(tasks);
        } catch (IOException e) {
            ui.showError();
        }
        ui.showCompleteMessage(tasks, index - 1);
    }
}
