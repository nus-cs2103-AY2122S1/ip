package main.java.duke.commands;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import main.java.duke.MainWindow;
import main.java.duke.Storage;
import main.java.duke.TaskList;
import main.java.duke.tasks.Deadline;
import main.java.duke.tasks.Event;
import main.java.duke.tasks.Task;

/**
 * A command that lists all tasks on the given date from the task list.
 */
public class OnDateCommand extends Command {
    private final String dateString;

    /**
     * Constructs a new find tasks on a date command with the given date.
     *
     * @param dateString the string of the date
     */
    public OnDateCommand(String dateString) {
        this.dateString = dateString;
    }

    /**
     * Executes the find tasks on a date command.
     *
     * @param tasks given list of tasks
     * @param gui given gui object
     * @param storage given storage object
     */
    public String execute(TaskList tasks, MainWindow gui, Storage storage) {
        return identifyTasksByDate(dateString, tasks);
    }

    private String identifyTasksByDate(String dateString, TaskList tasks) {
        LocalDate date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        ArrayList<Task> tasksOnDate = new ArrayList<>();
        ArrayList<Task> taskList = tasks.getTaskList();
        for (Task task : taskList) {
            if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                if (deadline.getDateFormatted() != null) {
                    if (deadline.getDateFormatted().equals(date)) {
                        tasksOnDate.add(deadline);
                    }
                }
            } else if (task instanceof Event) {
                Event event = (Event) task;
                if (event.getTimeFormatted() != null) {
                    LocalDate eventDate = event.getTimeFormatted().toLocalDate();
                    if (eventDate.equals(date)) {
                        tasksOnDate.add(event);
                    }
                }
            }
        }
        StringBuilder message = new StringBuilder(("On this day you have the following task(s):"));
        for (Task task : tasksOnDate) {
            message.append(task.toString());
        }
        return message.toString();
    }
}
