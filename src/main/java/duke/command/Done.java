package duke.command;

import duke.exception.NoSuchTaskException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Encapsulates a Done commands that deals with marking a task in the task list as done.
 *
 * @author Zhi Bin
 * @version Duke Level 9
 */
public class Done extends DukeCommand {
    private final int taskNumber;

    /**
     * Constructor for a Done Command.
     *
     * @param ui         The Ui handler that handles the printing of message with respect to the command.
     * @param storage    The storage handler that handles saving or loading data to local directory.
     * @param list       The TaskList handler that handles operation related to task.
     * @param taskNumber The task number that corresponds to the task that user want to mark as done.
     */
    public Done(Ui ui, Storage storage, TaskList list, int taskNumber) {
        super(ui, storage, list);
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the Delete command. Marks a task in the task list as done,
     * prints a message after marking, and update the local data file.
     *
     * @throws NoSuchTaskException When the task number is invalid.s
     */
    @Override
    public void execute() throws NoSuchTaskException{
        ui.markDoneMessage(list.mark(taskNumber));
        storage.save(list.getList());
    }
}
