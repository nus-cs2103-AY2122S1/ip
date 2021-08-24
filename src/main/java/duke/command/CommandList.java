package duke.command;

import duke.DukeUi;
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
    public void execute(TaskList tl) {
        DukeUi.printLine(tl.getTasks());
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
