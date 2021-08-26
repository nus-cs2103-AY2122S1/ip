package duke.commands;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;
import java.time.LocalDate;

/**
 * The TasksOnCommand class is a Command that returns the tasks which have deadlines/end dates on the input date
 */

public class TasksOnCommand extends Command{
    LocalDate date;

    /**
     * public constructor which is used to initialise the input date
     *
     * @param date input date which is used to retrieve the tasks
     */
    public TasksOnCommand(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Checks whether the list contains a deadline or event with the input date as a deadline/end date and
     * appends it to a temporary list and sends a call to the ui to display this temporary list of tasks
     *
     * @param taskList the list of tasks upon which the operations need to be performed
     * @param ui the ui in which a message relating to the command execution is displayed to the user
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
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
        ui.displayTasksOn(date, list);
    }
}
