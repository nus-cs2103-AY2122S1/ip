package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

/**
 * ListCommand class encapsulates command to list tasks.
 */
public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        int index = 1;
        for (Task task : tasks) {
            System.out.println(index + ". " + task);
            index++;
        }
    }
}
