package lania.command;

import lania.Storage;
import lania.Ui;
import lania.task.TaskList;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        ui.showListMessage(tasks);
    }
}
