package command;

import storage.Storage;

import task.TaskList;

import ui.Ui;

public class ExitCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbye();
    }

    /**
     * Returns true.
     * Exit the program.
     *
     * @return true
     */
    @Override
    public boolean isExit() {
        return true;
    }

}
