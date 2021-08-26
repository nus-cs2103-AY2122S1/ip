package duke.commands;

import duke.exceptions.InvalidNumberInputException;
import duke.exceptions.RepeatedDoneException;
import duke.tasks.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class DoneCommand extends Command{

    int index;

    public DoneCommand(int index) {
        this.index = index;
    }

    @Override
    public boolean isExit(){
        return false;
    }

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
