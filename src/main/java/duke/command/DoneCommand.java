package duke;

/**
 * Represents a command class that marks a task as done.
 *
 * @author Chen Hsiao Ting
 * @version CS2103T AY21/22 Semester 1
 */
public class DoneCommand extends Command {

    /**
     * A constructor for DoneCommand.
     *
     * @param tasks A list of current Tasks.
     * @param input User input.
     */
    public DoneCommand(TaskList tasks, String input) {
        super(tasks, input);
    }

    /**
     * Marks a task as done.
     *
     * @return String representation of the done task.
     * @throws OutOfBoundException If user enter an invalid index.
     */
    public String done() throws OutOfBoundException {
        Parser parser = new Parser(input);
        int index = parser.getIndex(tasks.getSize());
        return tasks.done(index);
    }
}
