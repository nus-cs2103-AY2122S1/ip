package duke.command;

import duke.Ui;
import duke.task.TaskList;

/**
 * The type List command that lists out all tasks from a given list of tasks.
 */
public class ListCommand extends Command {

    /** List of tasks to run the command on */
    private final TaskList tasks;

    /**
     * Instantiates a new List command.
     *
     * @param tasks list of tasks to list tasks from
     */
    public ListCommand(TaskList tasks) {
        this.tasks = tasks;
    }

    @Override
    public void execute() {
        System.out.println(
                Ui.tabAndFormat(this.tasks.toString())
        );
    }
}
