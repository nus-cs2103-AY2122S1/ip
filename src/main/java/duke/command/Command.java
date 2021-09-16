package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * An abstract class representing the command.
 */
public abstract class Command {
    private String type; // Type of command
    private boolean isExit; // If the user wants to exit

    public Command(String type) {
        this.type = type;
        this.isExit = false;
    }

    public String getType() {
        return type;
    }

    public boolean isExit() {
        return isExit;
    }

    public void setExit() {
        this.isExit = true;
    }

    public abstract void execute(TaskList taskList, Ui ui, Storage storage);
}
