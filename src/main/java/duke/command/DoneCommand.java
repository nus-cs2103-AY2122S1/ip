package duke.command;

import duke.TaskManager;

/**
 * Represents the command "done x" to mark a task at index x (1-indexed) as completed.
 */
public class DoneCommand extends Command {
    private final int id;

    public DoneCommand(int id) {
        this.id = id;
    }

    @Override
    public String execute(TaskManager taskManager) {
        return String.format(
                "Nice! I've marked this task as done: \n"
                        + "%s", taskManager.completeTask(id - 1).toString()
        );
    }
}
