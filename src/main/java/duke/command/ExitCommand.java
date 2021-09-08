package duke.command;

import duke.Action;
import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

public class ExitCommand extends Command {
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
    public void executeAndShow(TaskList taskList, Storage storage) {
        Ui.showMessage(execute(taskList, storage));
    }

    /**
     * Returns the result of executing the exit command.
     *
     * @param taskList The task list of duke.
     * @param storage  The local storage of duke.
     * @return A string representation of the result.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        assert super.getAction() == Action.BYE : "Exit command action type error";
        storage.writeToTaskTxt(taskList.getTasks());
        return "Bye. Hope to see you again soon!\n";
    }
}
