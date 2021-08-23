package duke.command;

import duke.*;

public class AddCommand extends Command {

    private final String input;

    public AddCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task toAdd = Task.createTask(input);
        taskList.addToList(toAdd);
        ui.taskCreatedMessage(toAdd, taskList);
    }
}
