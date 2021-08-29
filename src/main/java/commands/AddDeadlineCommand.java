package commands;

import exceptions.DukeInvalidDateException;
import tasks.Deadline;
import tasks.Task;
import tasks.TaskList;

/**
 * A command to add a Deadline task to the taskList.
 */
public class AddDeadlineCommand extends AddCommand {

    private final TaskList taskList;

    /**
     * Creates an AddDeadline Command.
     *
     * @param input The input by the user that triggers this command.
     * @param taskList The taskList for Duke.
     */
    public AddDeadlineCommand(String input, TaskList taskList) {
        super(input, Task.Type.DEADLINE);
        this.taskList = taskList;
    }

    @Override
    public boolean execute() {
        String details = this.removeFirstWordFromInput();
        if (details != null && this.verifyAddCommand(details.trim())) {
            Task task;
            try {
                task = Deadline.newDeadlineTask(details);
            } catch (DukeInvalidDateException e) {
                this.setExecutionMessage(e.getMessage() + "\n");
                return false;
            }
            this.setExecutionMessage(this.taskList.addTask(task));
            return true;
        }
        return false;
    }

    /**
     * Verifies that the deadline task details are correct. It checks that the user has used the
     * command "-by" or "/by" and that a non-empty date and time is specified. If it is not correct, it
     * prints an error message.
     *
     * @param input The deadline details.
     * @return True if the deadline details inputted by the user is correct. Otherwise, false.
     */
    @Override
    public boolean verifyAddCommand(String input) {
        if (!input.contains("-by") && !input.contains("/by")) {
            this.setExecutionMessage(this.getInvalidArgumentsMessage());
            return false;
        }
        String[] inputParts = input.split(" -by | /by ");
        if (inputParts.length != 2) {
            this.setExecutionMessage(this.getInvalidArgumentsMessage());
            return false;
        }
        return true;
    }
}
