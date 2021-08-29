package duke.command;


import duke.Ui;
import duke.task.TaskList;

public class ListCommand extends Command {

    private TaskList tasks;
    private Ui ui;

    public ListCommand(TaskList tasks, Ui ui) {
        this.tasks = tasks;
        this.ui = ui;
    }

    @Override
    public void execute() {
        ui.showList(tasks);
    }
}
