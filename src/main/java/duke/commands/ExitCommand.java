package duke.commands;

import duke.Storage;
import duke.TaskList;

public class ExitCommand extends Command {
    public ExitCommand(String desc) {
        super(desc);
    }

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public void execute(TaskList tasks, Storage storage) {

    }
}
