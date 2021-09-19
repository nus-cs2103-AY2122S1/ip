package duke.command;

import duke.task.TaskList;

/**
 * Represents a command to list.
 */
public class ListCommand implements Command {

    private final TaskList tasks;

    /**
     * Creates a list command.
     *
     * @param tasks Task list
     */
    public ListCommand(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Runs the list command.
     *
     * @return List string
     */
    @Override
    public String run() {
        return tasks.toString();
    }
}
