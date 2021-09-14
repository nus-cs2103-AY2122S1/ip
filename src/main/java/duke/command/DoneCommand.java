package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Marks a task as done.
 */
public class DoneCommand implements Command {
    public static final String COMMAND_IDENTIFIER = "done";

    private int taskIndex;

    /**
     * Returns the Done command represented by the user input.
     *
     * @param userInput String input provided by the user.
     * @return Done command.
     * @throws MalformedCommandException If userInput is incorrectly formatted for a Done command.
     */
    public static Command create(String userInput) throws MalformedCommandException {
        try {
            int taskIndex = Integer.parseInt(userInput.split(" ")[1]);
            return new DoneCommand(taskIndex);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new MalformedCommandException(
                    "Please provide a valid integer index for the task you want to mark as done like so: "
                            + "done [taskIndex in integer form]");
        }
    }

    private DoneCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public String execute(TaskList tasks, Ui ui) throws MalformedCommandException {
        Task taskMarkedDone = tasks.markTaskDone(taskIndex - 1);
        return ui.showTaskDoneMessage(taskMarkedDone);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
