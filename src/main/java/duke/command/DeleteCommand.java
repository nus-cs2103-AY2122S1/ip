package duke.command;

import duke.Ui;
import duke.task.TaskList;

/**
 * The type Delete command that deletes a user-specified tasks from a given list of tasks.
 */
public class DeleteCommand extends Command {

    /** User inputted string */
    private final String userInput;
    /** List of tasks to run the command on */
    private final TaskList tasks;

    /**
     * Instantiates a new Delete command.
     *
     * @param userInput user-inputted string
     * @param tasks     list of tasks to delete the user-specified task from
     */
    public DeleteCommand(String userInput, TaskList tasks) {
        this.userInput = userInput;
        this.tasks = tasks;
    }

    @Override
    public void execute() {
        String[] inputs = this.userInput.split(" ");
        int ind = Integer.valueOf(inputs[1]) - 1;

        String result = "Noted. I've removed this task:\n  " + tasks.get(ind).toString() + "\n";
        tasks.delete(ind);
        result += "Now you have " + tasks.size() + " tasks in the list.";
        System.out.println(Ui.tabAndFormat(result));
    }

}
