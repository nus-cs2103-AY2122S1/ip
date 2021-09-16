package duke.commands;

import java.time.LocalDateTime;

import duke.DukeException;
import duke.tasks.Deadline;
import duke.util.PersistentStorage;
import duke.util.Response;
import duke.util.Tasklist;

/**
 * Class that encapsulates a "deadline" command from the user.
 */
public class DeadlineCommand extends Command {

    /** Starting index of the description for a Deadline. */
    public static final int DEADLINE_DESC_START = 9;

    /** The String description of the Deadline provided by the user */
    private String description;

    /** The due datetime of the Deadline provided by the user */
    private LocalDateTime dueDateTime;

    /**
     * A Constructor for a DeadlineCommand
     *
     * @param description A String describing the Deadline.
     * @param dueDateTime A LocalDateTime describing the due datetime of the Deadline.
     */
    public DeadlineCommand(String description, LocalDateTime dueDateTime) {
        this.description = description;
        this.dueDateTime = dueDateTime;
    }

    /**
     * Executes the deadline command by creating the specified Deadline, adding it to the
     * Tasklist and displaying the updated Tasklist.
     *
     * @param taskList The Tasklist associated with the Duke instance.
     * @param response The UI associated with the Duke instance.
     * @param storage The PersistentStorage associated with the Duke instance.
     * @return A CommandResult detailing the added Task.
     */
    public CommandResult executeCommand(Tasklist taskList, Response response, PersistentStorage storage)
            throws DukeException {
        Deadline deadline = new Deadline(this.description, this.dueDateTime);
        taskList.addTask(deadline);
        storage.saveTasks(taskList);
        return new CommandResult(response.showAddedTask(taskList, deadline));
    }
}
