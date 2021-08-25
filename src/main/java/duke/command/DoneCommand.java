package duke.command;

import duke.exception.DukeException;
import duke.ui.Storage;
import duke.ui.TaskList;
import duke.ui.Ui;

import java.io.IOException;

public class DoneCommand extends Command { //DoneCommand to handle the updating of list
    private final int index;

    public DoneCommand(String input) throws DukeException {
        super(true);
        if (input == null) {
            //Catch if there is no number after the command
            throw new DukeException("☹ OOPS!!! done will require a task number to update.");
        }
        this.index = Integer.parseInt(input.trim());
        }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException, DukeException {
        if (index > taskList.getCount() || index <= 0) {
            //Catches if the number is > than the number of task or if its negative
            throw new DukeException("☹ OOPS!!! The number is not in within the number of tasks!");
        } else {
            Ui.doneMessage(taskList.get(index - 1).done());
            Storage.updateText(taskList);
        }
    }
}