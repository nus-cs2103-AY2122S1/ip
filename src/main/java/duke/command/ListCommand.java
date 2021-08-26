package duke.command;

import duke.TaskList;
import duke.Storage;
import duke.Ui;

public class ListCommand extends Command {
    public static final String COMMAND = "list";

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printList(taskList);
    }
}
