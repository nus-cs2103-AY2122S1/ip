package duke.commands;

import duke.exceptions.InvalidTaskNumberException;
import duke.utils.CliUi;
import duke.utils.Storage;
import duke.utils.TaskList;

/** Represents command to delete an item from the taskList */
public class DeleteTaskCommand extends Command {

    private int taskNumber;

    /**
     * DeleteTaskCommand constructor.
     *
     * @param taskNumber The index of the task to delete, counting from 1.
     */
    public DeleteTaskCommand(String taskNumber) {
        this.taskNumber = Integer.parseInt(taskNumber);
    }

    /**
     * Deletes the specified task from the taskList.
     *
     * @param taskList The list of tasks in Duke. Handles all task related functions.
     * @param cliUi Ui object to deal with user input/outputs.
     * @param storage Storage object to deal with saving taskList to disk.
     * @return String[] with the messages to be printed out to the Ui.
     * @throws InvalidTaskNumberException if the taskNumber is 0 or greater than the size of the taskList.
     */
    @Override
    public String execute(TaskList taskList, CliUi cliUi, Storage storage) throws InvalidTaskNumberException {
        String[] messages = taskList.deleteTask(taskNumber);
        cliUi.printOut(messages);
        storage.save(taskList);
        return String.join("\n", messages);
    }

    /**
     * Returns a String representation of the command.
     *
     * @return String representation of the command.
     */
    @Override
    public String toString() {
        return String.format("TO DELETE: index %d", taskNumber);
    }
}
