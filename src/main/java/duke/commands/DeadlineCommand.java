package duke.commands;

import duke.tasks.Deadlines;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;

/**
 * The command indicating that the user wants to add in a Deadline into the task list.
 */
public class DeadlineCommand extends Command {
    private String description;
    private String dateTimeBy;

    /**
     * Constructor for DeadlineCommand.
     *
     * @param description the description of the Deadline to be added.
     * @param dateTimeBy the date where this deadline is due.
     */
    public DeadlineCommand(String description, String dateTimeBy) {
        this.description = description;
        this.dateTimeBy = dateTimeBy;
    }

    /**
     * Performs the actions that adds the Deadline to the task list.
     *
     * @param tasks the full task list containing all the tasks.
     * @param ui the ui instance.
     * @param storage the storage instance.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Deadlines newDeadline = new Deadlines(description, dateTimeBy, false);
        tasks.addTask(newDeadline);
        ui.showTaskAddedInteraction(newDeadline, tasks);
        storage.addTaskToPersistedData(newDeadline);
    }
}
