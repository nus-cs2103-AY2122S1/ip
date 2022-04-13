package duke.commands;

import java.io.IOException;

import duke.TaskList;


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
     * Executes the command.
     *
     * @param tasks The task list.
     * @return The reply of Duke to the user.
     */
    public abstract String execute(TaskList tasks) throws IOException;
}
