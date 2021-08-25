package duke.command;

/**
 * AddCommand class encapsulates command to add new task.
 */
public abstract class AddCommand extends Command {
    /**
     * Description of the task
     */
    protected String description;

    /**
     * Constructs an AddCommand with the specified description.
     *
     * @param description Description of the command.
     */
    public AddCommand(String description) {
        this.description = description;
    }
}
