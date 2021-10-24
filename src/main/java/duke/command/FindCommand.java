package duke.command;

import duke.TaskList;

/**
 * Represents a find command.
 */
public class FindCommand extends Command {

    private String task;

    public FindCommand(String input) {
        this.task = input.split(" ")[1];
    }

    /**
     * Returns the result of the execution of the find command.
     * @param tasks List of tasks the user has added.
     * @return Result of the execution of the find command.
     */
    @Override
    public String execute(TaskList tasks) {
        return tasks.findTasks(this.task);
    }
}
