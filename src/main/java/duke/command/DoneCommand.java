package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class DoneCommand extends Command{
    private int index;

    /**
     * Returns true if a valid done operation is entered.
     * False otherwise.
     *
     * @param input User input of the commands.
     * @return If the input contains a valid done operations.
     */
    public static boolean isDoneOps(String input) {
        int length = input.length();
        if (length < 6) {
            return false;
        }
        try {
            Integer.parseInt(input.substring(5));
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    public DoneCommand(String command) throws DukeException {
        if (isDoneOps(command)) {
            index = Integer.parseInt(command.substring(5)) - 1;
        } else {
            throw new DukeException("☹ Would you specify the duke.task for me my dear?");
        }
    }
    
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (index > taskList.size()) {
            throw new DukeException("☹ oopsie!!! The specified duke.task does not exit.");
        }
        taskList.doneTask(index);
        ui.showDone(taskList.getTask(index));
        storage.updateStorage(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
