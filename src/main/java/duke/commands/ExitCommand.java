package duke.commands;

import duke.DukeException;
import duke.TaskList;
import duke.ui.TextUi;

public class ExitCommand extends Command {

    @Override
    public String execute(TaskList tasks) throws DukeException {
        return TextUi.showGoodbyeMessage();
    }
}
