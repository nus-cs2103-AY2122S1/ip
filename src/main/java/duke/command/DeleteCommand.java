package duke.command;

import duke.DukeException;

public class DeleteCommand extends Command {
    private int index;

    /**
     * Returns true if a valid delete operation is entered.
     * False otherwise.
     *
     * @param input User input of the commands.
     * @return If the input contains a valid done operations.
     */
    public static boolean isDeleteOps(String input) {
        int length = input.length();
        if (length < 8) {
            return false;
        }
        try {
            Integer.parseInt(input.substring(7));
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public DeleteCommand(String command) throws DukeException {
        if (isDeleteOps(command)) {
            index = Integer.parseInt(command.substring(7)) - 1;
        } else {
            throw new DukeException("☹ Would you specify the duke.task for me my dear?");
        }
    }
    
    @Override
    public void execute(duke.TaskList taskList, duke.Ui ui, duke.Storage storage) throws DukeException {
        if (index > taskList.size()) {
            throw new DukeException("☹ oopsie!!! The specified duke.task does not exit.");
        }
        duke.task.Task holder = taskList.getTask(index);
        taskList.deleteTask(index);
        ui.showDelete(holder, taskList.size());
        storage.updateStorage(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
