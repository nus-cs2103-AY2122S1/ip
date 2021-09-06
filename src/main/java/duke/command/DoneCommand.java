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
        if (this.indexToMarkAsDone == -1) {
            String message = formatAndMarkAllAsDone(taskList);

            ui.print(message);
        } else {
            String message = formatAndMarkDoneAtIndex(taskList);

            ui.print(message);
        }
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
        if (this.indexToMarkAsDone == -1) {
            String message = formatAndMarkAllAsDone(taskList);

            return message;
        } else {
            String message = formatAndMarkDoneAtIndex(taskList);

            return message;
        }
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

        for (int i = 0; i < taskList.getSize(); i++) {
            taskList.markAsDone(i);
            if (i == taskList.getSize() - 1) {
                message += i + 1 + "." + taskList.taskToString(i);
            } else {
                message += i + 1 + "." + taskList.taskToString(i) + "\n";
            }
        }
        return message;
    }
}
