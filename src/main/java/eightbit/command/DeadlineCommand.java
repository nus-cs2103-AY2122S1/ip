package eightbit.command;

import eightbit.task.Deadline;
import eightbit.util.Storage;
import eightbit.util.TaskList;
import eightbit.util.Ui;

/**
 * Represents a command to add a deadline.
 */
public class DeadlineCommand extends Command {

    private final Deadline deadline;

    /**
     * Constructs a command to add a deadline.
     *
     * @param deadline Deadline instance to be added.
     */
    public DeadlineCommand(Deadline deadline) {
        this.deadline = deadline;
    }

    /**
     * Adds the deadline into the user's list.
     *
     * @param taskList User's list of tasks.
     * @param ui Ui responsible for printing messages.
     * @param storage Storage responsible for reading/writing the file.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.add(deadline);
        storage.appendToFile(deadline);
        ui.printWithLines("Got it. I've added this task:\n  " + deadline
                + "\nNow you have " + taskList.size() + " tasks in the list.");
    }
}
