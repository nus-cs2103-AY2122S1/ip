package duke.commands;

import duke.exceptions.InvalidNumberInputException;
import duke.exceptions.RepeatedDoneException;
import duke.tasks.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;


/**
 * The DoneCommand is a Command that marks a task as done in the list
 *
 */
public class DoneCommand extends Command {

    int index;

    /**
     * Public constructor which initialises the value of the index of the task to be marked done
     *
     * @param index the index of task to be marked done
     */
    public DoneCommand(int index) {
        this.index = index;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Checks if a task is already done, in which case a RepeatedDoneException is thrown. Else it marks the
     * task as done. If an invalid index is entered, an InvalidNumberInputException is thrown.
     *
     * @param taskList the list of tasks upon which the operations need to be performed
     * @param ui the ui in which the result message of the command execution is displayed to the user
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        try {
            Task currTask = taskList.get((index - 1));
            if (currTask.getStatus()) {
                throw new RepeatedDoneException();
            } else {
                currTask.markAsDone();
            }
            ui.displayDone(currTask.toString());
        } catch (IndexOutOfBoundsException | NumberFormatException | NullPointerException ex) {
            throw new InvalidNumberInputException();
        }
    }

}
