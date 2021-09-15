package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.task.TimedTask;

/**
 * The type Reschedule command.
 */
public class RescheduleCommand extends Command {

    private final int idx;
    private final TaskList tasks;
    private final String dateString;
    private final String[] timeArgs;

    /**
     * Instantiates a new Reschedule command.
     *
     * @param idx        the index of the task to reschedule.
     * @param tasks      the list of tasks that contains the task to reschedule.
     * @param dateString the new date constraint for the task.
     * @param timeArgs   the new time constraint for the task.
     */
    public RescheduleCommand(int idx, TaskList tasks, String dateString, String... timeArgs) {
        assert tasks != null : "TaskList cannot be null.";
        assert dateString != null : "Date constraint cannot be null.";
        this.idx = idx;
        this.tasks = tasks;
        this.dateString = dateString;
        this.timeArgs = timeArgs;
    }

    @Override
    public String execute() throws IllegalArgumentException {
        Task current = tasks.get(idx);
        if (!(current instanceof TimedTask)) {
            throw new IllegalArgumentException("The task you have chosen does not have date/time parameters.");
        }
        String result = "Your task:\n" + tasks.get(idx).toString() + "\nhas been rescheduled to:\n";
        TimedTask curr = (TimedTask) current;
        curr.scheduleTask(dateString, timeArgs);
        result += current;
        return result;
    }

}
