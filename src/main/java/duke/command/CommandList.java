package duke.command;

import duke.ContactsList;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * The CommandList class handles the command "list" that handles the command
 * to print out all tasks in the task list.
 *
 * @author Loh Wen Hao Aaron
 *
 */
public class CommandList extends Command {
    public static final String KEYWORD = "list";

    public CommandList() {
    }

    @Override
    public void execute(TaskList tl, Storage st, Ui ui, ContactsList cl) {
        tl.printAllTasks();
    }

}
