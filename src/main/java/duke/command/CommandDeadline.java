package duke.command;

import duke.DukeUi;
import duke.task.Deadline;
import duke.task.TaskList;

/**
 * Represents a command to add a Deadline task.
 */
public class CommandDeadline extends DukeCommand {
    private Deadline task;

    /**
     * Creates a new CommandDeadline.
     *
     * @param t Deadline to be added.
     */
    public CommandDeadline(Deadline t) {
        this.task = t;
    }

    /**
     * Adds the Deadline task to the task list.
     *
     * @param tl Task list for the user.
     */
    @Override
    public void execute(TaskList tl) {
        DukeUi.printLine(tl.addTask(task));
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
