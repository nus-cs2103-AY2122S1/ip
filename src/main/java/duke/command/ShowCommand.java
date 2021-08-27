package duke.command;

import duke.Action;
import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

public class ShowCommand extends Command{

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
    public void execute(TaskList taskList, Storage storage) {
        Ui.showTasks(taskList.getTasks());
    }
}
