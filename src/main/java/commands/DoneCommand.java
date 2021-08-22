package commands;

import tasks.TaskList;

/**
 * A command to marks a task in Duke's taskList as done.
 */
public class DoneCommand implements Command {

    private final String input;
    private final TaskList taskList;

    /**
     * Creates a DoneCommand to mark a task in the taskList as completed.
     *
     * @param input The input by the user that triggers this command.
     * @param taskList The taskList that contains the task to be mark as done.
     */
    public DoneCommand(String input, TaskList taskList) {
        this.input = input;
        this.taskList = taskList;
    }

    @Override
    public void execute() {
        try {
            int index = Integer.parseInt(input.split(" ", 2)[1].trim());
            this.taskList.markTaskAsCompleted(index);
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
        System.out.println("Invalid argument to the \"done\" function.\n");
    }
}
