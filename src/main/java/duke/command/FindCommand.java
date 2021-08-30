package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;

public class FindCommand extends Command {
    private final String target;

    public FindCommand(String target) {
        this.target = target;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.listFoundTasks(tasks.searchList(target));
    }
}
