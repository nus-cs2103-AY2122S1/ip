package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Deletes a task from the user's list of tasks.
 */
public class DeleteCommand implements Command {
    public static final String COMMAND_IDENTIFIER = "delete";

    private int taskIndex;

    /**
     * Returns the Delete command represented by the user input.
     *
     * @param userInput String input provided by the user.
     * @return Delete command.
     * @throws MalformedCommandException If userInput is incorrectly formatted for a Delete command.
     */
    public static Command create(String userInput) throws MalformedCommandException {
        assert userInput != null : "User input should be null for the creation of a Command";

        try {
            int taskIndex = Integer.parseInt(userInput.split(" ")[1]);
            return new DeleteCommand(taskIndex);
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            throw new MalformedCommandException("Please provide a valid integer index for the task"
                    + " you want to delete like so: " + "delete [taskIndex in integer form]");
        }
    }

    private DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public String execute(TaskList tasks, Ui ui) throws MalformedCommandException {
        Task taskDeleted = tasks.delete(taskIndex - 1);
        int numTasksRemaining = tasks.numTasks();
        return ui.showTaskDeletedMessage(taskDeleted, numTasksRemaining);
    }
}
