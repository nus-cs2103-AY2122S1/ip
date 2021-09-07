package eightbit.command;

import eightbit.task.Deadline;
import eightbit.util.Storage;
import eightbit.util.TaskList;

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
     * @param storage  Storage responsible for reading/writing the file.
     * @return The response after executing the command.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        assert deadline != null : "Deadline should be initialized";

        taskList.add(deadline);
        storage.appendToFile(deadline);
        return "Got it. I've added this task:\n  " + deadline
                + "\nNow you have " + taskList.size() + " tasks in the list.";
    }
}
