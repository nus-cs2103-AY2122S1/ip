package duke.commands;

import duke.DukeException;
import duke.Tasklist;
import duke.UI;
import duke.PersistentStorage;
import duke.tasks.Deadline;

import java.time.LocalDateTime;

/**
 * Class that encapsulates a "deadline" command from the user.
 */
public class DeadlineCommand extends Command {

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
     * @param ui The UI associated with the Duke instance.
     * @param storage The PersistentStorage associated with the Duke instance.
     */
    public void executeCommand(Tasklist taskList, UI ui, PersistentStorage storage) {
        Deadline deadline = new Deadline(this.description, this.dueDateTime);
        taskList.addTask(deadline);
        ui.showAddedTask(taskList, deadline);
    }
}
