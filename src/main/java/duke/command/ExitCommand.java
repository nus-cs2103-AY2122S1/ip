package duke.command;

import duke.Action;
import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

public class ExitCommand extends Command{
    /**
     * Constructs an exit command using the given action and set isExit to true.
     *
     * @param action The given action.
     */
    public ExitCommand(Action action) {
        super(action, true);
    }

    /**
     * Exit the current process.
     *
     * @param taskList The task list of duke.
     * @param storage The local storage of duke.
     */
    @Override
    public void execute(TaskList taskList, Storage storage) {
        Ui.bye();
        storage.writeToTaskTxt(taskList.getTasks());
    }
}
