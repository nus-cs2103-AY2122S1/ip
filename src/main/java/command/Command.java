package command;

import duke.TaskList;

/**
 * Represents a command to be executed on the task list.
 */
public abstract class Command {
    private boolean isBye;

    public Command() {
        isBye = false;
    }
    
    public Command(boolean isBye) {
        this.isBye = isBye;
    }

    /**
     * Executes the command.
     *
     * @param taskList The TaskList used in the command.
     */
    public abstract void execute(TaskList taskList);
    
    public boolean isBye() {
        return isBye;
    }
}
