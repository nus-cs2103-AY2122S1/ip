package commands;

import tasks.TaskList;

/**
 * A command to delete a task from Duke's taskList.
 */
public class DeleteCommand extends Command {

    private final String input;
    private final TaskList taskList;

    /**
     * Creates a DeleteCommand to delete a task from the taskList.
     *
     * @param input The input by the user that triggers this command.
     * @param taskList The taskList to remove the task from.
     */
    public DeleteCommand(String input, TaskList taskList) {
        this.input = input;
        this.taskList = taskList;
    }

    @Override
    public boolean execute() {
        try {
            // Checks if an argument is provided
            int index = Integer.parseInt(input.split(" ", 2)[1].trim());
            this.setExecutionMessage(this.taskList.removeTask(index));
            return true;
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            this.setExecutionMessage(this.getInvalidArgumentsMessage());
            return false;
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String getInvalidArgumentsMessage() {
        return "Invalid argument to the \"delete\" function.\n";
    }
}
