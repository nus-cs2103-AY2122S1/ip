package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

import java.util.ArrayList;

public class FindCommand extends Command {
    public FindCommand(String[] args) {
        super(args);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> filteredTasks = tasks.searchTasks(args[0]);
        ui.showFilteredTasks(filteredTasks);
    }

    public boolean isExit() {
        return false;
    }
}
