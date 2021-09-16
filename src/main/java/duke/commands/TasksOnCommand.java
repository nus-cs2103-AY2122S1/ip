package duke.commands;

import java.time.LocalDate;

import duke.tasklist.TaskList;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.ui.Ui;

/**
 * The TasksOnCommand class is a Command that returns the tasks which have deadlines/end dates on the input date
 */
public class TasksOnCommand extends Command {

    private final LocalDate date;

    /**
     * Initialises the input date which is used to search the list
     *
     * @param date input date which is used to retrieve the tasks
     */
    public TasksOnCommand(LocalDate date) {
        this.date = date;
    }

    /**
     * Checks whether the list contains a deadline or event with the input date as a deadline/end date and
     * appends it to a temporary list
     *
     * @param taskList the list of tasks upon which the operations need to be performed
     * @param ui the ui in which the result message of the command execution is displayed to the user
     */
    @Override
    public String execute(TaskList taskList, Ui ui) {
        TaskList list = new TaskList();
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            if (task instanceof Deadline) {
                if (((Deadline) task).getDate().equals(date)) {
                    list.add(task);
                }
            }
            if (task instanceof Event) {
                if (((Event) task).getDate().equals(date)) {
                    list.add(task);
                }
            }
        }
        return ui.displayTasksOn(date, list);
    }
}
