package duke.commands;

import duke.data.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Encapsulates the List command's operations
 */
public class ListCommand extends Command {
    public ListCommand() {
        super();
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.print(String.format("Here are the tasks in your list:%s", tasks.printList()));
    }
}
