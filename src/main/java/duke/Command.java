package duke;

/**
 * Encapsulates an action that the user wants to make.
 */
public interface Command {

    /**
     * Carries out the command.
     *
     * @param tasks the current list of tasks.
     * @param ui user interface interacts with the user.
     * @param storage custodian of reading and writing save files.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Identifies if this command is an exit command.
     *
     * @return whether this command is an exit command.
     */
    public boolean isExit();
}
