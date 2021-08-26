package command;

import duke.TaskList;

public class ListCommand extends Command {
    
    public ListCommand() {
        super();
    }

    /**
     * Executes the command of displaying the given task list.
     *
     * @param taskList The task list to be displayed.
     */
    @Override
    public void execute(TaskList taskList) {
        taskList.showList();
    }
}
