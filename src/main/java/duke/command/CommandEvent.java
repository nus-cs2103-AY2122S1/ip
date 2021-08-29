package duke.command;

import duke.DukeUi;
import duke.task.Event;
import duke.task.TaskList;

/**
 * Represents a command to add an Event task.
 */
public class CommandEvent extends DukeCommand {
    private Event task;

    /**
     * Creates a new CommandEvent.
     *
     * @param t Event to be added.
     */
    public CommandEvent(Event t) {
        this.task = t;
    }

    /**
     * Adds the event to the task list.
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
