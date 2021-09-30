package duke.command;

import duke.TaskList;

/**
 * Represents a delete command.
 */
public class DeleteCommand extends Command {

    private int task;

    public DeleteCommand(String input) {
        this.task = Integer.parseInt(input.split(" ")[1]);
    }

    /**
     * Returns the result of the execution of the delete command.
     * @param tasks List of tasks the user has added.
     * @return Result of the execution of the delete command.
     */
    @Override
    public String execute(TaskList tasks) {
        return tasks.deleteTask(this.task);
    }
}
