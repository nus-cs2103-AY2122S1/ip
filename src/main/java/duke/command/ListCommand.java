package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;

public class ListCommand extends Command{
    private final TaskList taskList;
    private final Ui textUi;

    public ListCommand(TaskList taskList, Ui ui) {
        this.taskList = taskList;
        this.textUi = ui;
    }

    @Override
    public String execute() {
        return textUi.list(taskList);
    }
}
