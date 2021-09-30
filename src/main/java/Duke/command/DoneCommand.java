package Duke.command;

import Duke.TaskList;

/**
 * Represents a done command.
 */
public class DoneCommand extends Command {

    private int task;

    public DoneCommand(String input) {
        this.task = Integer.parseInt(input.split(" ")[1]);
    }

    /**
     * Returns the result of the execution of the done command.
     * @param tasks List of tasks the user has added.
     * @return Result of the execution of the done command.
     */
    @Override
    public String execute(TaskList tasks) {
        return tasks.markAsDone(this.task);
    }
}
