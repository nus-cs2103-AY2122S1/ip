package duke.commands;

import duke.exceptions.*;
import duke.TaskList;
import duke.Storage;
import duke.Ui;

/**
 * This is a duke.commands.ListCommand class that extends duke.commands.Command.
 */
public class ListCommand extends Command {

    public ListCommand() {
        super("list");
    }


    @Override
    public void execute(TaskList taskList, Storage store, Ui ui)
            throws EmptyListException {
            taskList.printTasks(ui);
    }
}
