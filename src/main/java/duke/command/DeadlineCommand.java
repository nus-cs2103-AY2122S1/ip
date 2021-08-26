package duke.command;

import duke.core.Storage;
import duke.core.TaskList;
import duke.task.Deadline;

/**
 * Encapsulates a command that handles the addition of deadline tasks into the task list.
 */
public class DeadlineCommand extends Command {
    private Deadline deadlineTask;

    /**
     * Constructs a DeadlineCommand object.
     *
     * @param taskName Description of the deadline task.
     * @param time Deadline of the task.
     */
    public DeadlineCommand(String taskName, String time) {
        deadlineTask = new Deadline(taskName, time);
    }

    /**
     * Adds the deadline task into the task list and storage file.
     *
     * @param taskList The TaskList object storing all the tasks.
     * @param storage The Storage object which was instantiated with the appropriate storage filepath.
     */
    @Override
    public void execute(TaskList taskList, Storage storage) {
        taskList.addTask(deadlineTask);
        storage.saveTasksToFile(taskList);
    }

    /**
     * Returns a boolean specifying whether Duke should terminate.
     *
     * @return false
     */
    @Override
    public boolean shouldExit() {
        return false;
    }
}