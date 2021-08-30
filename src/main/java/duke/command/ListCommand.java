package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ListCommand extends Command {
    public ListCommand(String type) {
        super(type);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            displayTasksList(taskList, ui);
        } catch (DukeException e) {
            ui.displayError(e.getMessage());
        }
    }

    public void displayTasksList(TaskList taskList, Ui ui) throws DukeException {
        if (taskList.size() != 0) {
            String response = ui.getTasksInList(taskList);
            ui.displayResponse(response);
        } else {
            throw new DukeException("There are no tasks in your list!");
        }
    }
}
