package duke.commands;

import duke.exceptions.InvalidTaskNumberException;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.CliUi;

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

    @Override
    public String execute(TaskList tasks, CliUi cliUi, Storage storage) throws InvalidTaskNumberException {
        String[] messages = tasks.deleteTask(taskNumber);
        cliUi.printOut(messages);
        storage.save(tasks);
        return String.join("\n", messages);
    }

    @Override
    public String toString() {
        return String.format("TO DELETE: index %d", taskNumber);
    }
}
