package duke.commands;

import duke.exceptions.InvalidTaskNumberException;
import duke.utils.CliUi;
import duke.utils.Storage;
import duke.utils.TaskList;

/** Represents command to list all values in the current taskList */
public class MarkDoneCommand extends Command {

    private int taskNumber;

    /**
     * MarkDoneCommand constructor.
     *
     * @param taskNumber The index of the task to mark as done, counting from 1.
     */
    public MarkDoneCommand(String taskNumber) {
        this.taskNumber = Integer.parseInt(taskNumber);
    }

    /**
     * Marks the specified task as done.
     *
     * @param taskList The list of tasks in Duke. Handles all task related functions.
     * @param cliUi Ui object to deal with user input/outputs.
     * @param storage Storage object to deal with saving taskList to disk.
     * @return String[] with the messages to be printed out to the Ui.
     * @throws InvalidTaskNumberException if the taskNumber is 0 or greater than the size of the taskList.
     */
    @Override
    public String execute(TaskList taskList, CliUi cliUi, Storage storage) throws InvalidTaskNumberException {
        String[] messages = taskList.markDone(taskNumber);
        cliUi.printOut(messages);
        storage.save(taskList);
        return String.join("\n", messages);
    }

    @Override
    public String toString() {
        return String.format("TO MARK DONE: index %d", taskNumber);
    }
}
