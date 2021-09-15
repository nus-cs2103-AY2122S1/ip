package duke.command;


import duke.Ui;
import duke.task.TaskList;

public class ListCommand extends Command {

    private TaskList tasks;
    private Ui ui;

    /**
     * Instantiates command to print the list of current tasks for the user.
     *
     * @param tasks The current list of the user's tasks.
     * @param ui Ui to handle interactions.
     */
    public ListCommand(TaskList tasks, Ui ui) {
        this.tasks = tasks;
        this.ui = ui;
    }

    @Override
    public void execute() {
        ui.showList(tasks);
    }
}
