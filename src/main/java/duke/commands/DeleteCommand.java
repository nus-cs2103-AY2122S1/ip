package duke.commands;

import duke.exceptions.InvalidNumberInputException;
import duke.tasks.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class DeleteCommand extends Command {

    int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        try {
            Task currTask = taskList.remove(index - 1);
            ui.displayDelete(currTask.toString(), taskList);
        } catch (IndexOutOfBoundsException | NumberFormatException | NullPointerException ex) {
            throw new InvalidNumberInputException();
        }
    }
}
