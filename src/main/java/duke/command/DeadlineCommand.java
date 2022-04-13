package duke.command;

import duke.core.Storage;
import duke.core.TaskList;
import duke.task.Deadline;

import java.io.IOException;

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
     * Adds the deadline task into the task list and storage file and returns the output to be displayed by Duke.
     *
     * @param taskList The TaskList object storing all the tasks.
     * @param storage The Storage object which was instantiated with the appropriate storage filepath.
     * @return The output to be displayed by Duke.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        String output = taskList.addTask(deadlineTask);
        try {
            storage.saveTasksToFile(taskList);
        } catch (IOException e) {
            output = String.format("%s\n%s", output, e.getMessage());
        }

        return output;
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