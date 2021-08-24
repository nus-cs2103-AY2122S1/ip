package command;

import duke.TaskList;
import duke.Ui;

public class ListCommand extends Command {
    
    public ListCommand() {
        super();
    }

    /**
     * Executes the command of displaying the given task list.
     *
     * @param ui Ui not used in this execution.
     * @param taskList The task list to be displayed.
     */
    @Override
    public void execute(Ui ui, TaskList taskList) {
        taskList.showList();
    }
}
