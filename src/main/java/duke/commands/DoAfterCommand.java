package duke.commands;

import java.time.LocalDateTime;

import duke.DukeException;
import duke.tasks.DoAfter;
import duke.util.PersistentStorage;
import duke.util.Response;
import duke.util.Tasklist;

/**
 * The type Do after command.
 */
public class DoAfterCommand extends Command {

    /** Starting index of the description for a DoAfter */
    public static final int DOAFTER_DESC_START = 8;

    /** The String description of the DoAfter provided by the user. */
    private String description;

    /** The do after datetime of the DoAfter provided by the user. */
    private LocalDateTime doAfterDateTime;

    /**
     * A Constructor for a DoAfterCommand
     *
     * @param description A String describing the DoAfter.
     * @param doAfterDateTime A LocalDateTime describing the do after datetime of the DoAfter.
     */
    public DoAfterCommand(String description, LocalDateTime doAfterDateTime) {
        this.description = description;
        this.doAfterDateTime = doAfterDateTime;
    }

    /**
     * Executes the deadline command by creating the specified DoAfter, adding it to the
     * Tasklist and displaying the updated Tasklist.
     *
     * @param taskList The Tasklist associated with the Duke instance.
     * @param response The UI associated with the Duke instance.
     * @param storage The PersistentStorage associated with the Duke instance.
     * @return A CommandResult detailing the added Task.
     */
    @Override
    public CommandResult executeCommand(Tasklist taskList, Response response, PersistentStorage storage)
            throws DukeException {
        DoAfter doAfter = new DoAfter(this.description, this.doAfterDateTime);
        taskList.addTask(doAfter);
        storage.saveTasks(taskList);
        return new CommandResult(response.showAddedTask(taskList, doAfter));
    }
}
