package duke;

/**
 * Represents an abstract Command class.
 *
 * @author Chen Hsiao Ting
 * @version CS2103T AY21/22 Semester 1
 */
public abstract class Command {
    protected TaskList tasks;
    protected String input;

    /**
     * A constructor for Command.
     *
     * @param tasks A list of current Tasks.
     * @param input User input.
     */
    public Command(TaskList tasks, String input) {
        this.tasks = tasks;
        this.input = input;
    }
}
