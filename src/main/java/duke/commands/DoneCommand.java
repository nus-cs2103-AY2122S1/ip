package duke.commands;

import duke.exceptions.DukeException;
import duke.functionality.Storage;
import duke.functionality.Ui;
import duke.tasks.Task;

/**
 * Represents the command when the user wants to mark a task as done.
 */
public class DoneCommand extends Command {
    private int taskNum;

    public DoneCommand (int taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Executes the command and marks the specified task as done.
     *
     * @param storage The storage system of the bot involved with this command.
     * @param ui The ui of the bot involved with this command.
     */
    public void execute(Storage storage, Ui ui) {
        try {
            Task taskToMark = storage.getTask(taskNum);
            taskToMark.markDone();
            storage.saveToFile();
            ui.markedDoneMessage(taskToMark);
        } catch (IndexOutOfBoundsException e) {
            Ui.showErrorMessage("Please enter a valid index!");
        }
    }

    public boolean isExit() {
        return false;
    }
}
