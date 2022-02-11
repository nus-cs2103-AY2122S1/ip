package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

public class InvalidCommand extends Command {

    public InvalidCommand() {
        super(false);
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.display("OOPS! I do not understand what does that mean. Maybe you can try either one of "
                + "[todo, deadline, event, done, list, delete]?");
    }
}
