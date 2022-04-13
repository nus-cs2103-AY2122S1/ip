package duke.command;

import duke.Action;
import duke.Storage;
import duke.StringUtils;
import duke.Ui;
import duke.task.TaskList;

public class ShowCommand extends Command {

    /**
     * Constructs a show command instance using the given action.
     *
     * @param action The given action.
     */
    public ShowCommand(Action action) {
        super(action);
    }

    /**
     * Executes the command.
     *
     * @param taskList The task list of duke.
     * @param storage  The local storage of duke.
     */
    @Override
    public void executeAndShow(TaskList taskList, Storage storage) {
        Ui.showMultiLines(execute(taskList, storage));
    }

    /**
     * Returns the result of executing the show command.
     *
     * @param taskList The task list of duke.
     * @param storage  The local storage of duke.
     * @return A string representation of the result.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        assert super.getAction() == Action.LIST : "Show command action type error";
        return StringUtils.getTaskList(taskList.getTasks());
    }
}
