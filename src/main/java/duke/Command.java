package duke;

/**
 * Abstract class representing all commands.
 */
public abstract class Command {

    /**
     * Abstract run method depending on the command.
     *
     * @param tasks TaskList containing all tasks.
     * @param ui Ui to display to the user.
     * @param storage Storage to store tasks.
     * @return String to be displayed.
     */
    public abstract String run(TaskList tasks, Ui ui, Storage storage);



}
