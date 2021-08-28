package duke;

/**
 * Represents a generic command.
 */
public abstract class Command {
    /**
     * Executes the command.
     *
     * @param tasks Task list component.
     * @param storage Storage component.
     * @param ui UI component.
     */
    public abstract void execute(TaskList tasks, Storage storage, Ui ui);
}
