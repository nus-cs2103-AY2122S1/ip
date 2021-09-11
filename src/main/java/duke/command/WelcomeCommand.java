package duke.command;

import duke.Duke;
import duke.Storage;
import duke.task.TaskList;

/**
 * WelcomeCommand class encapsulates Duke's welcome.
 */
public class WelcomeCommand extends Command {
    @Override
    public void execute(Duke duke, TaskList tasks, Storage storage) {
        String message = "Jiii~ \nWhat can I do for you?";
        duke.setResponse(message);
    }
}
