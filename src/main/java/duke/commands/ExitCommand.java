package duke.commands;

import duke.DukeException;
import duke.DukeStorage;
import duke.TaskList;
import duke.Ui;

public class ExitCommand extends Command {

    public ExitCommand(){}

    @Override
    public void execute(TaskList taskList, Ui ui, DukeStorage storage) throws DukeException {
        try {
            ui.byeMessage();
            storage.writeTasks(taskList);
        } catch (DukeException error) {
            System.out.println(error.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
