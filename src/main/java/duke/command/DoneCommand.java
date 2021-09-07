package duke.command;

import duke.ArchiveList;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Representation for the done command of Duke.
 */
public class DoneCommand extends Command {
    private final int indexToMarkAsDone;
    private final boolean isMarkAll;

    /**
     * Constructor for DoneCommand.
     *
     * @param indexToMarkAsDone Index of taskList to mark as done.
     */
    public DoneCommand(int indexToMarkAsDone) {
        this.indexToMarkAsDone = indexToMarkAsDone;
        this.isMarkAll = indexToMarkAsDone == -1;
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
     * @param archiveList ArchiveList to store archived tasks.
     * @param ui Ui to print to users of Duke.
     * @param storage Storage to save and load TaskList for Duke.
     */
    @Override
    public void execute(TaskList taskList, ArchiveList archiveList, Ui ui, Storage storage) {
        String message;

        if (this.isMarkAll) {
            message = formatAndMarkAllAsDone(taskList);

        } else {
            message = formatAndMarkDoneAtIndex(taskList);

        }
        ui.print(message);
    }

    /**
     * Gets the String representation of the things printed in the
     * execute method as well as execute the marking of the task as done.
     *
     * @param taskList TaskList to mark Task at index indexToMarkAsDone as Done.
     * @param archiveList ArchiveList to store archived tasks.
     * @param ui Ui to get the String representation of the text printed.
     * @param storage Storage to save and load TaskList for Duke.
     */
    @Override
    public String getExecutedString(TaskList taskList, ArchiveList archiveList, Ui ui, Storage storage) {
        String message;
        if (this.isMarkAll) {
            message = formatAndMarkAllAsDone(taskList);

        } else {
            message = formatAndMarkDoneAtIndex(taskList);

        }
        return message;
    }

    /**
     * Formats and marks task at indexToMarkAsDone done in given taskList.
     *
     * @param taskList taskList to mark task at indexToMarkAsDone done.
     * @return Returns formatted String to be printed to the user.
     */
    private String formatAndMarkDoneAtIndex(TaskList taskList) {
        taskList.markAsDone(this.indexToMarkAsDone);

        return "Nice! I've marked this task as done:\n" + "  "
                + taskList.taskToString(this.indexToMarkAsDone);
    }

    /**
     * Formats and marks all tasks as done in given taskList.
     *
     * @param taskList taskList to mark all tasks as done.
     * @return Returns formatted String to be printed to the user.
     */
    private String formatAndMarkAllAsDone(TaskList taskList) {
        String message = "Noted. I've marked these tasks as done:\n";

        taskList.markAllAsDone();

        for (int i = 0; i < taskList.getSize(); i++) {
            if (i == taskList.getSize() - 1) {
                message += i + 1 + "." + taskList.taskToString(i);
            } else {
                message += i + 1 + "." + taskList.taskToString(i) + "\n";
            }
        }
        return message;
    }
}
