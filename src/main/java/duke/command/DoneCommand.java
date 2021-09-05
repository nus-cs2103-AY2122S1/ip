package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Representation for the done command of Duke.
 */
public class DoneCommand extends Command {
    private final int indexToMarkAsDone;

    /**
     * Constructor for DoneCommand.
     *
     * @param indexToMarkAsDone Index of taskList to mark as done.
     */
    public DoneCommand(int indexToMarkAsDone) {
        this.indexToMarkAsDone = indexToMarkAsDone;
    }

    /**
     * Checks if this is an exit command.
     *
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the DoneCommand.
     *
     * @param taskList TaskList to mark Task at index indexToMarkAsDone as Done.
     * @param ui Ui to print to users of Duke.
     * @param storage Storage to save and load TaskList for Duke.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.markAsDone(this.indexToMarkAsDone);
        String message = "Nice! I've marked this task as done:\n" + "  "
                + taskList.taskToString(this.indexToMarkAsDone);
        ui.print(message);
    }

    /**
     * Gets the String representation of the things printed in the
     * execute method as well as execute the marking of the task as done.
     *
     * @param taskList TaskList to mark Task at index indexToMarkAsDone as Done.
     * @param ui Ui to get the String representation of the text printed.
     * @param storage Storage to save and load TaskList for Duke.
     */
    @Override
    public String getExecutedString(TaskList taskList, Ui ui, Storage storage) {
        taskList.markAsDone(this.indexToMarkAsDone);
        return "Nice! I've marked this task as done:\n" + "  "
                + taskList.taskToString(this.indexToMarkAsDone);
    }
}
