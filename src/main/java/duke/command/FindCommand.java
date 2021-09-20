package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class FindCommand extends Command{
    private final TaskList taskList;
    private final Ui textUi;
    private final String keyword;


    public FindCommand(TaskList taskList, Ui ui, String keyword) {
        this.taskList = taskList;
        this.textUi = ui;
        this.keyword = keyword;
    }

    @Override
    public String execute() {
        return textUi.find(taskList, keyword);
    }
}
