package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * OnCommand class handles the 'on' command to list the tasks on a date.
 */
public class OnCommand extends Command {

    private LocalDate date;

    /**
     * Constructs the OnCommand object.
     *
     * @param date Date queried for the 'on' command.
     */
    public OnCommand(LocalDate date) {
        this.date = date;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
        StringBuilder message = new StringBuilder("The tasks that you have on " + date.format(formatter) + " are:\n");
        for (int i = 0; i < taskList.getSize(); i++) {
            Task task = taskList.getTask(i);
            if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                if (deadline.getBy().isEqual(date)) {
                    message.append(deadline).append("\n");
                }
            } else if (task instanceof Event) {
                Event event = (Event) task;
                if (event.getTime().isEqual(date)) {
                    message.append(event).append("\n");
                }
            }
        }
        return message.toString();
    }
}
