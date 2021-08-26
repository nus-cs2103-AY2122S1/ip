package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * This abstract class provides the framework for Commands to be implemented.
 */
public abstract class Command {

    /** The execute method to be implemented by the Commands. */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    /** The boolean that determines if a Command terminates the user interaction. */
    private boolean isExit;

    /** The types of Commands. */
    public enum CommandType {
        ADD,
        DELETE,
        DONE,
        UNDO,
        ERROR,
        EXIT,
        LIST,
        SEARCH
    }

    /** The Command type for the object in question. */
    private CommandType commandType;

    /** Checks if the Command is terminating. */
    public boolean isExit() {
        return isExit;
    }

    /**
     * The constructor for the Command object.
     *
     * @param commandType The Command type
     * @param isExit Boolean if its terminating or not
     */
    public Command(CommandType commandType, boolean isExit) {
        this.commandType = commandType;
        this.isExit = isExit;
    }

    /**
     * This method gets the Command type of the object.
     *
     * @return The Command's type
     */
    public CommandType getCommandType() {
        return this.commandType;
    }
}
