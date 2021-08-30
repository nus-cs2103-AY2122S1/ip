package duke.command;

import duke.exception.BadInputFormatException;
import duke.exception.NoSuchTaskException;
import duke.util.Storage;
import duke.util.TaskList;

/** Represents the "done" command. */
public class DoneCommand extends Command {
    /** The index of the Task to be marked as done. */
    private int index;

    /**
     * DoneCommand constructor.
     *
     * @param index The index of the Task to be marked as done.
     */
    private DoneCommand(int index) {
        this.index = index;
    }

    /**
     * DoneCommand factory method.
     *
     * @param index The index of the Task to be deleted as a numeric string.
     * @return A DoneCommand object.
     * @throws BadInputFormatException If the provided input string is not a numeric.
     */
    public static DoneCommand of(String index) throws BadInputFormatException {
        try {
            return new DoneCommand(Integer.parseInt(index) - 1);
        } catch (NumberFormatException e) {
            throw new BadInputFormatException();
        }
    }

    /**
     * Marks the Task at the specified index as done.
     *
     * @param tasks The list of tasks in the program.
     * @param storage The storage utility.
     * @throws NoSuchTaskException If the task does not exist at the specified index.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws NoSuchTaskException {
        return formatOutput("Nice and spicy, I've marked this task as done:",
                tasks.getTask(index).markTaskAsDone().toString());
    }

    /**
     * Tests if a command is exit.
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
