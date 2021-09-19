package duke.logic.commands;

/**
 * Lists out all current tasks in the task list.
 */
public class ListCommand extends Command {

    /**
     * Lists all tasks of the task list.
     *
     * @return The execution result.
     */
    @Override
    public CommandResult execute() {
        return new CommandResult(getTaskList().toString());
    }

}
