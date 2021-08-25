package duke.command;

import duke.Ui;
import duke.Storage;
import duke.DukeException;

import duke.task.TaskList;

public class ExitCommand extends Command {

    public ExitCommand(String userInput) {
        super(userInput);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ui.sayBye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
