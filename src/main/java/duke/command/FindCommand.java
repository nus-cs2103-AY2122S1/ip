package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;

public class FindCommand extends Command {
    private final TaskList taskList;
    private final Ui textUi;
    private final String keyword;

    /**
     * Constructor of FindCommand class. Initialize a FindCommand instance
     * from a given TaskList, Ui, taskInfo, keyword.
     *
     * @param taskList A list of tasks
     * @param ui A user interface
     * @param keyword A keyword usd by user to search task
     */
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
