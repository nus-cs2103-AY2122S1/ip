package duke.command;

import duke.task.TaskList;

/**
 * Represents command to display the current task list.
 */
public class CommandList extends DukeCommand {
    /**
     * Displays the task list.
     *
     * @param tl Task list for the user.
     */
    @Override
    public String execute(TaskList tl) {
        return tl.getTasks();
    }

    /**
     * As described in DukeCommand.
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
