package duke.command;


import duke.task.TaskList;
import duke.Ui;

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
