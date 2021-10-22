package duke.command;

import duke.Storage;
import duke.Ui;

import duke.exception.DukeException;
import duke.exception.InvalidInputException;
import duke.task.TaskList;

import java.io.IOException;

/**
 * This class encapsulates a command to mark a task as 'Done'.
 * Extends Command class.
 */
public class MarkDoneCommand extends Command {

    private int taskNumber;

    /**
     * Constructor for a MarkDoneCommand.
     * @param taskList TaskList from which to mark Task as done.
     * @param storage Storage involved in the command.
     * @param ui Ui involved in the command.
     * @param taskNumber Number of the Task to be marked as done.
     */
    public MarkDoneCommand(TaskList taskList, Storage storage, Ui ui, int taskNumber) {
        super(taskList, storage, ui);
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the Command.
     */
    @Override
    public void execute() throws IndexOutOfBoundsException {
        try {
            taskList.setDone(taskNumber);
            storage.save(taskList);
            ui.setMessage("Alrighty, marking this task as done:\n" + taskList.get(taskNumber));
        } catch (IOException e) {
            ui.showError(new DukeException("Unable to Save\n" + e.getMessage()));
        } catch (IndexOutOfBoundsException e) {
            ui.showError(new InvalidInputException("Invalid input, please enter a number from 1 to "
                    + taskList.getSize()));
        }
    }
}