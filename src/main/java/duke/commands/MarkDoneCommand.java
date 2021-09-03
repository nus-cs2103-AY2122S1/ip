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

    @Override
    public String execute(TaskList tasks, CliUi cliUi, Storage storage) throws InvalidTaskNumberException {
        String[] messages = tasks.markDone(taskNumber);
        cliUi.printOut(messages);
        storage.save(tasks);
        return String.join("\n", messages);
    }

    @Override
    public String toString() {
        return String.format("TO MARK DONE: index %d", taskNumber);
    }
}
