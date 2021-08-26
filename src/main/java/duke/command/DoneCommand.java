package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class DoneCommand implements Command {
    public static final String COMMAND_IDENTIFIER = "done";

    private int taskIndex;

    public static Command create(String userInput) throws MalformedCommandException {
        try {
            int taskIndex = Integer.parseInt(userInput.split(" ")[1]);
            return new DoneCommand(taskIndex);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new MalformedCommandException(
                "Please provide a valid integer index for the task you want to mark as done like so: " +
                    "done [taskIndex in integer form]");
        }
    }

    private DoneCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) throws MalformedCommandException {
        Task taskMarkedDone = tasks.markTaskDone(taskIndex - 1);
        ui.showTaskDoneMessage(taskMarkedDone);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
