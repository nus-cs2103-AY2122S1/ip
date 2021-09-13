package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;

public class ExitCommand extends Command {
    public static final String MESSAGE_OUTRO = "Bye. Come back soon!";

    @Override
    public String execute(TaskList tasks, Storage storage) {
        System.exit(0);
        return MESSAGE_OUTRO;
    }

    /**
     * Returns true.
     * Exit the program.
     *
     * @return true
     */
    @Override
    public boolean isExit() {
        return true;
    }

}
