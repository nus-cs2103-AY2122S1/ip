package duke.commands;

import duke.tasks.TaskList;
import duke.utils.Storage;

public class ExitCommand extends Command {
    private static final String EXIT_MSG = "Goodbye Courier!";

    @Override
    public String execute(TaskList tasks, Storage storage) {
        return EXIT_MSG;
    }
}
