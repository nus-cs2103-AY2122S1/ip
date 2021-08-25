package duke.command;

public abstract class AddCommand extends Command {
    /**
     * Description of the task
     */
    protected String description;

    /**
     * Constructs an AddCommand with the specified description.
     *
     * @param description
     */
    public AddCommand(String description) {
        this.description = description;
    }
}
