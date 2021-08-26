package command;

import storage.Storage;

import task.TaskList;

import ui.Ui;

public class ExitCommand extends Command {
    public ExitCommand() {}

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {}

    @Override
    public boolean isExit() {
        return true;
    }
}
