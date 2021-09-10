package duke.commands;

import java.time.LocalDate;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ReminderCommand extends Command {

    /**
     * Displays the current TaskList being used.
     *
     * @param taskList The current TaskList being used.
     * @param ui The current Ui being used.
     * @param storage The current Storage being used.
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        LocalDate reminderDate = LocalDate.now().plusDays(7);
        TaskList remindTasks = taskList.getTasksBefore(reminderDate);
        return ui.showReminderTasks(remindTasks);
    }
}
