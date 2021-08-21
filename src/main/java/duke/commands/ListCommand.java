package duke.commands;

import duke.data.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

public class ListCommand extends Command {
    public ListCommand() {
        super();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.print(tasks.printList());
    }
}
