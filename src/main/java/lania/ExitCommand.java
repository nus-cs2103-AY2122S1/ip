package lania;

import lania.task.TaskList;

public class ExitCommand extends Command {

    public void execute(TaskList tasks, Storage storage, Ui ui) {
        this.isExit = true;
        ui.showExitMessage();
    }
}
