package duke.command;

import duke.Storage;
import duke.Ui;

import duke.exception.DukeException;
import duke.exception.InvalidInputException;
import duke.task.Task;
import duke.task.TaskList;

import java.io.IOException;

/**
 * This class encapsulates a command to delete a Task from a TaskList.
 * Extends Command class.
 */
public class DeleteCommand extends Command {

    private int taskNumber;

    /**
     * Constructor for a DeleteCommand.
     * @param taskList TaskList from which to delete Task.
     * @param storage Storage involved in the command.
     * @param ui Ui involved in the command.
     * @param taskNumber Number of the Task to be deleted.
     */
    public DeleteCommand(TaskList taskList, Storage storage, Ui ui, int taskNumber) {
        super(taskList, storage, ui);
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the Command.
     */
    @Override
    public void execute() {
        try {
            Task task = taskList.get(taskNumber);
            taskList.delete(taskNumber);
            storage.save(taskList);
            ui.setMessage("Okay then, I've removed this from the list:\n" + task +
                    "\nNumber of tasks in list: " + taskList.getSize());
        } catch (IOException e) {
            ui.showError(new DukeException("Unable to Save\n" + e.getMessage()));
        } catch (IndexOutOfBoundsException e) {
            ui.showError(new InvalidInputException("Invalid input, please enter a number from 1 to "
                    + taskList.getSize()));
        }
    }
}
