package duke.command;

import duke.Storage;
import duke.Ui;

import duke.task.TaskList;

import java.io.IOException;

/**
 * This class encapsulates a command to mark a task as 'Done'.
 * Extends Command class.
 */
public class MarkDoneCommand extends Command {

    private int taskNumber;

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
            ui.showMessage("Alrighty, marking this task as done:\n" + taskList.get(taskNumber));
        } catch (IOException e) {
            ui.showError("Error: Unable to Save\n" + e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            ui.showError("Error: Invalid input, please enter a number from 1 to " + taskList.getSize());
        }
    }
}