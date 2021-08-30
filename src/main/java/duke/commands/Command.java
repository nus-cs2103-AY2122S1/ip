package duke.commands;

import duke.Storage;
import duke.TaskList;

import java.io.IOException;

/**
 * Abstract class for commands.
 */
public abstract class Command {
    private String desc;

    /**
     * Constructor for Command.
     *
     * @param desc description of the command.
     */
    public Command(String desc) {
        this.desc = desc;
    }

    /**
     * Returns the description of the command.
     *
     * @return the description of the command.
     */
    public String getDesc() {
        return this.desc;
    }

    /**
     * Returns if the command is the exit command.
     */
    public abstract boolean isExit();

    /**
     * Executes the command. Adds deadline to task list. Updates the save file.
     */
    public abstract void execute(TaskList tasks) throws IOException;
}
