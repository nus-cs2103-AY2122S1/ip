package duke.command;

import duke.*;
import duke.exception.DukeException;

public class AddCommand extends Command {

    private final String input;

    public AddCommand(String input) {
        this.input = input;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task toAdd = Task.createTaskFromInput(input);
        taskList.addToList(toAdd);
        return ui.getAddMessage(toAdd, taskList);
    }
}
