package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

public class ByeCommand extends Command {

    public static final String COMMAND_WORD = "bye";

    @Override
    public String execute(TaskList tasks, Storage storage) {
        return Ui.getGoodbyeMessage();
    }
}
