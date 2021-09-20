package duke.command;

import duke.DateTime;
import duke.Storage;
import duke.TaskList;
import duke.exception.DukeException;
import duke.exception.InvalidCommandException;
import duke.exception.InvalidDateTimeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.ui.Ui;

import java.time.LocalDate;

public class ViewScheduleCommand extends Command {
    private final LocalDate date;

    /**
     * Creates a command that views the schedule for a given day.
     *
     * @param arguments Command arguments.
     */
    public ViewScheduleCommand(String arguments) throws InvalidCommandException {
        if (arguments.length() == 0) {
            throw new InvalidCommandException("Command `schedule` requires an arguments");
        }

        try {
            this.date = DateTime.parseDate(arguments);
        } catch (InvalidDateTimeException e) {
            throw new InvalidCommandException("Unable to parse date from arguments: " + arguments);
        }
    }

    /**
     * Displays the schedule for the given date.
     *
     * @param taskList The tasklist.
     * @param ui The instance of the {@link Ui} class.
     * @param storage The instance of the {@link Storage} class.
     * @throws DukeException when unable to find task or when unable to save tasks.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        TaskList filteredTaskList = taskList.filterDate(date);

        StringBuilder builder = new StringBuilder();
        int numTasks = filteredTaskList.size();

        builder.append("Schedule for ")
                .append(date)
                .append("\n\n");

        if (numTasks == 0) {
            builder.append("Empty");
        } else {
            for (int i = 0; i < numTasks; i++) {
                Task item = filteredTaskList.getTask(i);
                builder.append(i + 1)
                        .append(". ")
                        .append(taskSchedule(item));
                if (i < numTasks - 1) {
                    builder.append("\n");
                }
            }
        }

        ui.printMessage(builder.toString());
    }

    private String taskSchedule(Task task) {
        if (task instanceof Event) {
            return DateTime.stringifyTime(((Event) task).getTime()) + ": " + task.getDescription() + " (Event)"
                    + (task.getIsCompleted() ? " (Done)" : "");
        }

        if (task instanceof Deadline) {
            return DateTime.stringifyTime(((Deadline) task).getTime()) + ": " + task.getDescription() + " (Deadline)"
                    + (task.getIsCompleted() ? " (Done)" : "");
        }

        return "";
    }
    
    @Override
    public boolean shouldExit() {
        return false;
    }
}
