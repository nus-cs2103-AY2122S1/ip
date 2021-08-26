package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class FindCommand extends Command {

    private String query;

    public  FindCommand(String query) {
        this.query = query;
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        StringBuilder message = new StringBuilder("Here are the matching tasks in your list:\n");
        int n = 1;
        for (int i = 0; i < taskList.getSize(); i++) {
            Task task = taskList.getTask(i);
            if (task.getDescription().contains(query)) {
                message.append(n).append(".").append(task).append("\n");
                n++;
            }
        }
        ui.printMessage(message.toString());
    }
}
