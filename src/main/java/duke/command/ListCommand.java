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
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.respondToList(tasks.getTasks());
    }

    @Override
    public Boolean isExit() {
        return false;
    }
}
