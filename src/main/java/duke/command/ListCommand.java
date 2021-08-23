package duke.command;

import duke.Storage;
import duke.UI;
import duke.task.TaskList;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        tasks.printTaskList(ui);
    }
}
