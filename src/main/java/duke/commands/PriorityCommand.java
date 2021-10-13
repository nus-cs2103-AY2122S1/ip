package duke.commands;

import duke.DukeException;
import duke.Task;
import duke.TaskList;

/**
 * This class encapsulates a Priority Command.
 *
 * @author Kleon Ang
 */
public class PriorityCommand extends Command {
    public PriorityCommand(TaskList tasks) {
        super(tasks);
    }

    @Override
    public String getReply(String arguments) throws DukeException {
        if (arguments.equals("")) {
            throw new DukeException("☹ OOPS!!! The priority level cannot be empty.");
        }
        assert !arguments.equals("") : "Arguments required, cannot be empty.";
        String[] splitArguments = arguments.split(" ");
        int taskIndex = Integer.parseInt(splitArguments[0]);
        String priorityToSet = splitArguments[1];
        return priority(taskIndex, priorityToSet);
    }

    /**
     * Sets a given task to a given priority level.
     *
     * @param taskIndex The index of the task to set priority.
     * @param priorityToSet A string indicating which priority to set the task to.
     * @return A string containing a success message for setting a priority.
     * @throws DukeException A Duke-specific exception that may occur when setting priority.
     */
    private String priority(int taskIndex, String priorityToSet) throws DukeException {
        if (taskIndex <= 0 || taskIndex > tasks.size()) {
            throw new DukeException("Sorry, no such task of index " + taskIndex + ".");
        }
        Task taskToSetPriority = tasks.get(taskIndex - 1);
        taskToSetPriority.setPriority(priorityToSet.toUpperCase());
        return "Nice! I've marked this task as " + taskToSetPriority.getPriority()
                + " priority :\n  " + taskToSetPriority;
    }
}
