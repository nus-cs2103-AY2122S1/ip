package duke.command;

import duke.TaskManager;

/**
 * Represents the command "exit", returns True when checked for isExit().
 */
public class ExitCommand extends Command {
    @Override
    public String execute(TaskManager taskManager) {
        return "Bye. Hope to see you again soon!";
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
