package duke.command;

import duke.exception.BadInputFormatException;
import duke.exception.NoSuchTaskException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/** Represents the "delete" command. */
public class DeleteCommand extends Command {
    /** The index of the Task to be deleted. */
    private int index;

    /**
     * DeleteCommand constructor.
     *
     * @param index The index of the Task to be deleted.
     */
    private DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * DeleteCommand factory method.
     *
     * @param index The index of the Task to be deleted as a numeric string.
     * @return A DeleteCommand object.
     * @throws BadInputFormatException If the provided input string is not a numeric.
     */
    public static DeleteCommand of(String index) throws BadInputFormatException {
        try {
            return new DeleteCommand(Integer.parseInt(index) - 1);
        } catch (NumberFormatException e) {
            throw new BadInputFormatException();
        }
    }

    /**
     * Removes the Task from the specified index in storage.
     *
     * @param tasks The list of tasks in the program.
     * @param ui The UI object.
     * @param storage The storage utility.
     * @throws NoSuchTaskException If the task does not exist at the specified index.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NoSuchTaskException {
        ui.print("Noted. I've removed this task:",
                tasks.deleteTask(index - 1).toString(),
                String.format("Now you have %d %s in the list.", tasks.size(), tasks.size() == 1 ? "task" : "tasks"));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
