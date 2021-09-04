package duke.command;

import duke.util.TaskList;

/**
 * Class that represents the Command to displays tasks from the TaskList.
 *
 * @author Benedict Chua
 */
public class RemindCommand extends Command {
    private TaskList tasks;
    private String reminderType;
    private String filterType = "reminder";

    /**
     * Constructor for ReminderCommand.
     *
     * @param tasks TaskList containing current tasks.
     * @param reminderType Type of filtering to do when listing tasks for reminder.
     */
    public RemindCommand(TaskList tasks, String reminderType) {
        assert reminderType.matches("today|week|next") : "Invalid reminderType";

        this.tasks = tasks;
        this.reminderType = reminderType;
    }

    /**
     * {@inheritDoc}
     *
     * This executes the list tasks command.
     */
    @Override
    public String execute() {
        switch (this.reminderType) {
        case "today":
            return tasks.listTasks(this.filterType, "0");
        case "week":
            return tasks.listTasks(this.filterType, "6");
        case "next":
            return tasks.getNextDueTask();
        default:
            throw new AssertionError("ReminderType is invalid");
        }
    }
}
