package duke;

/**
 * Represents a command class that deletes a task.
 *
 * @author Chen Hsiao Ting
 * @version CS2103T AY21/22 Semester 1
 */
public class DeleteCommand extends Command {

    /**
     * A constructor for DeleteCommand.
     *
     * @param tasks A list of current Tasks.
     * @param input User input.
     */
    public DeleteCommand(TaskList tasks, String input) {
        super(tasks, input);
    }

    /**
     * Deletes a task from the current list of Tasks.
     *
     * @return String representation of the deleted task.
     * @throws OutOfBoundException If user enter an invalid index.
     */
    public String delete() throws OutOfBoundException {
        Parser parser = new Parser(input);
        int index = parser.getIndex(tasks.getSize());
        return "Noted. I've removed this task: \n" + tasks.delete(index) +
                "\nNow you have " + (tasks.getSize()) + " tasks in the list.";
    }
}
