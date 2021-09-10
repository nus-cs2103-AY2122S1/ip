package duke.command;

import duke.Storage;
import duke.task.Task;
import duke.TaskList;
import duke.Ui;

public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the command.
     *
     * @param taskList the TaskList used during execution.
     * @param storage the Storage used during execution.
     * @param ui the Ui used during execution.
     */
    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) {
        String duplicateString = taskList.getDuplicate(task);
        if (duplicateString != null) {
            return ui.getDuplicateMessage(duplicateString);
        }
        taskList.addTask(task);
        storage.add(task);
        return ui.getAddMessage(task.toString(), taskList);
    }

    /**
     * Checks whether the command is the exit command.
     *
     * @return Whether the command is the exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
