package duke.command;

import duke.Ui;
import duke.task.TaskList;

/**
 * The type Done command that marks a user-specified task as done from a given list of tasks.
 */
public class DoneCommand extends Command {

    /** User inputted string */
    private final String userInput;
    /** List of tasks to run command on */
    private final TaskList tasks;

    /**
     * Instantiates a new Done command.
     *
     * @param userInput user-inputted string.
     * @param tasks     list of tasks to mark a task done from.
     */
    public DoneCommand(String userInput, TaskList tasks) {
        this.userInput = userInput;
        this.tasks = tasks;
    }

    @Override
    public void execute() {
        String[] inputs = this.userInput.split(" ");
        int ind = Integer.valueOf(inputs[1]) - 1;
        tasks.markDone(ind);
        String result = "Nice! I've marked this task as done:\n  "
                + tasks.get(ind).toString();
        System.out.println(Ui.tabAndFormat(result));
    }
}
