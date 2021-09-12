package duck.command;

import duck.TaskList;

import java.time.LocalDate;

/**
 * Represents the command that displays a schedule of tasks on a given date.
 */
public class ShowScheduleCommand extends Command {
    private final LocalDate desiredDate;

    /**
     * Constructor for a FindDateCommand.
     *
     * @param desiredDate Find tasks that take place on this date.
     */
    public ShowScheduleCommand(LocalDate desiredDate) {
        super();
        this.desiredDate = desiredDate;
    }

    /**
     * Executes the command of showing the schedule of tasks on the given date.
     *
     * @param taskList The task list from which tasks taking place on the given date are taken to be listed.
     * @return String representing the list of tasks found.
     */
    @Override
    public String execute(TaskList taskList) {
        return taskList.showSchedule(desiredDate);
    }
}
