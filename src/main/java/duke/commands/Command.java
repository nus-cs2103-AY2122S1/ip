package duke.commands;

import duke.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Represents an executable command
 */
public abstract class Command {
    private boolean isActive = true;
    private String commandOutput = "Your command hasn't been executed yet.";

    public Command() {
    }

    /**
     * Executes the command and returns the result.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public boolean isActive() {
        return isActive;
    }

    /**
     * Exits the program.
     */
    public void exit() {
        isActive = false;
    }

    public String getCommandOutput() {
        return commandOutput;
    }

    protected void setCommandOutput(String output) {
        commandOutput = output;
    };
}
