package duke.command;

import duke.Storage;
import duke.task.TaskList;

public abstract class UpdateCommand extends Command {

    public static final String COMMAND_WORD = "update";

    @Override
    public abstract String execute(TaskList tasks, Storage storage);

}
