package commands;

import tasks.TaskList;

/**
 * A command to delete a task from Duke's taskList.
 */
public class DeleteCommand implements Command {

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

    /**
     * Removes a task from the taskList based on the user's input.
     */
    @Override
    public void execute() {
        try {
            int index = Integer.parseInt(input.split(" ", 2)[1].trim());
            this.taskList.removeTask(index);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            this.invalidArgumentsProvided();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void invalidArgumentsProvided() {
        System.out.println("Invalid argument to the \"delete\" function.\n");
    }
}
