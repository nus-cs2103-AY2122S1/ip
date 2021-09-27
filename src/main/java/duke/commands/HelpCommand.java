package duke.commands;

import duke.data.TaskList;
import duke.data.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;

public class HelpCommand extends Command {
    public HelpCommand() {
        super();
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String addTasksHelp = "Add tasks using the following commands:"
                + "\ntodo <description>"
                + "\ndeadline <description> /by <date & time>"
                + "\nevent <description> /at <date & time>";
        String taskRelatedHelp = "Manage your task list/tasks using the following commands:"
                + "\nlist - view your tasks"
                + "\ndone <index> - mark task <index> as done"
                + "\ndelete <index> - remove task <index>"
                + "\nfind <keywords> - search for tasks by keywords"
                + "\nbye - say goodbye to Duke :(";
        return ui.print(addTasksHelp + "\n" + taskRelatedHelp);
    }
}
