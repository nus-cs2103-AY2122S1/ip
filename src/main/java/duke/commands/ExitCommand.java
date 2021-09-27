package duke.commands;

import duke.TaskList;

/**
 * This class handles command meant for quitting Duke.
 */
public class ExitCommand implements Command {

    @Override
    public String execute(TaskList taskList) {
        return "bye";
    }
}
