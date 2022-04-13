package duke.commands;

import duke.DukeException;
import duke.TaskList;
import duke.ui.TextUi;

public class ListCommand extends Command {
    public ListCommand() {}

    @Override
    public String execute(TaskList tasks) throws DukeException {
        return tasks.printList();
    }
}
