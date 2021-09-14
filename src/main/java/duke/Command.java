package duke;

/**
 * Represents a generic command.
 */
public abstract class Command implements Cloneable {
    /**
     * Executes the command.
     *
     * @param tasks Task list component.
     * @param storage Storage component.
     * @param ui UI component.
     */
    public abstract void execute(TaskList tasks, Storage storage, Ui ui);
    @Override
    public abstract String toString();

    @Override
    public Command clone() {
        try {
            return (Command) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new DukeException("Command cannot be cloned.");
        }
    }
}
