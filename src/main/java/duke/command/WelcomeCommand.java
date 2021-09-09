package duke.command;

import duke.Duke;
import duke.Storage;
import duke.task.TaskList;

/**
 * WelcomeCommand class encapsulates Duke's welcome.
 */
public class WelcomeCommand extends Command {
    private static final String LOGO = " ____        _        \n"
        + "|  _ \\ _   _| | _____ \n"
        + "| | | | | | | |/ / _ \\\n"
        + "| |_| | |_| |   <  __/\n"
        + "|____/ \\__,_|_|\\_\\___|\n";

    @Override
    public void execute(Duke duke, TaskList tasks, Storage storage) {
        String message = LOGO + "\nHello! I'm Duke.\nWhat can I do for you?";
        duke.setResponse(message);
    }
}
