package command;

import duke.TaskList;
import duke.Ui;

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
     * @param ui Ui used to display a message from the command's execution.
     * @param taskList The TaskList used in the command.
     */
    public abstract void execute(Ui ui, TaskList taskList);
    
    public boolean isBye() {
        return isBye;
    }
}
