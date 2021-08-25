package kermit.command;

import kermit.Storage;
import kermit.ToDo;
import kermit.Ui;

public class ListTasksCommand extends Command {

    public ListTasksCommand() {}

    @Override
    public void execute(ToDo taskList, Ui ui, Storage storage) {
        ui.showListItems(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}