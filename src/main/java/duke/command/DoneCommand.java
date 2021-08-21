package duke.command;

import duke.exception.BadInputFormatException;
import duke.exception.NoSuchTaskException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

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
     * @param ui The UI object.
     * @param storage The storage utility.
     * @throws NoSuchTaskException If the task does not exist at the specified index.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NoSuchTaskException {
        ui.print("Nice! I've marked this task as done:", tasks.getTask(index).markTaskAsDone().toString());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
