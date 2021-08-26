package duke.command;

import duke.Storage;
import duke.Ui;
import duke.tasks.TaskList;


public class ListCommand extends Command {
    String command;
    public ListCommand(String command) {
        this.command = command;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.respondToList(tasks.getTasks());
    }

    @Override
    public Boolean isExit() {
        return false;
    }
}
