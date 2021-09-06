package duke.command;

import duke.task.TaskList;

/**
 * Represents a dummy command that doesn't do anything.
 */
public class CommandGeneric extends DukeCommand {

    /**
     * Does nothing.
     *
     * @param tl Task list for the user.
     */
    @Override
    public String execute(TaskList tl) {
        return "Shuba Shuba Shuba!";
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
