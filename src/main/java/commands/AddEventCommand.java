package commands;

import exceptions.DukeInvalidDateException;
import tasks.Event;
import tasks.Task;
import tasks.TaskList;

/**
 * A command to add an Event task to the taskList.
 */
public class AddEventCommand extends AddCommand {

    private final TaskList taskList;

    /**
     * Creates an AddEventCommand.
     *
     * @param input The input by the user that triggers this command.
     * @param taskList The taskList for Duke.
     */
    public AddEventCommand(String input, TaskList taskList) {
        super(input, Task.Type.EVENT);
        this.taskList = taskList;
    }

    @Override
    public boolean execute() {
        String details = this.removeFirstWordFromInput();
        if (details != null && this.verifyAddCommand(details.trim())) {
            Task task;
            try {
                task = Event.newEventTask(details);
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
     * Verifies the event task details are correct. It checks that the user has used the command
     * "-at" or "/at" and that a non-empty date and time is specified. If is not correct, it prints
     * an error message.
     *
     * @param input The event details.
     * @return True if the event details inputted by the user is correct. Otherwise, false.
     */
    @Override
    public boolean verifyAddCommand(String input) {
        if (!input.contains("-at") && !input.contains("/at")) {
            this.setExecutionMessage(this.getInvalidArgumentsMessage());
            return false;
        }
        String[] inputParts = input.split(" -at | /at ");
        if (inputParts.length != 2) {
            this.setExecutionMessage(this.getInvalidArgumentsMessage());
            return false;
        }
        return true;
    }
}
