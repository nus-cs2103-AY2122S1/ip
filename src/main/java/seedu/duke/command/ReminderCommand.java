package seedu.duke.command;

import seedu.duke.DukeException;
import seedu.duke.Ui;
import seedu.duke.task.Task;
import seedu.duke.task.TaskList;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a reminder command. A <code>ReminderCommand</code> returns
 * all the upcoming tasks.
 */
public class ReminderCommand extends Command {
    private static final String REMINDER_MESSAGE = "These are the upcoming tasks:\n";
    private static final String NO_REMINDERS_MESSAGE = "There are no upcoming tasks.";
    private HashMap<LocalDate, ArrayList<Task>> dateTasks;

    /**
     * Public constructor for a <code>ReminderCommand</code>.
     *
     * @param ui The Ui to handle user interactions.
     * @param taskList The task list to be updated.
     * @param dateTasks A HashMap classifying the tasks based on the date.
     */
    public ReminderCommand(Ui ui, TaskList taskList,
                      HashMap<LocalDate, ArrayList<Task>> dateTasks) {
        super(ui, taskList);
        this.dateTasks = dateTasks;
    }

    /**
     * Returns the format on how to use the command.
     *
     * @return String representation of the help message.
     */
    @Override
    public String getUsageMessage() {
        return "reminder | get the upcoming tasks";
    }

    private TaskList filterTasks() {
        LocalDate now = LocalDate.now();
        ArrayList<Task> upcomingTasksList = new ArrayList<>();

        List<LocalDate> dates = dateTasks.keySet()
                .stream()
                .filter(x -> x.compareTo(now) > 0)
                .sorted()
                .collect(Collectors.toList());

        for (LocalDate date: dates) {
            System.out.println(date);
            upcomingTasksList.addAll(
                    dateTasks.getOrDefault(date, new ArrayList<>())
            );
        }
        return new TaskList(null, upcomingTasksList);
    }

    /**
     * Returns tasks happening or due at a later date than now.
     */
    @Override
    public String execute() throws DukeException {
        TaskList upcomingTasksTaskList = this.filterTasks();

        if (upcomingTasksTaskList.isEmpty()) {
            return NO_REMINDERS_MESSAGE;
        }

        return String.format("%s\n%s",
                REMINDER_MESSAGE,
                upcomingTasksTaskList.toString());
    }

}

