package duke.commands;

import duke.ui.Ui;
import duke.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.task.Task;

public class DeleteCommand extends Command{
    private String commands;

    public DeleteCommand(String commands) {
        this.commands = commands;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        String response = "";
        if (commands.matches("\\d+")) {
            int index = Integer.parseInt(commands) - 1;
            if (index >= 0 && index < tasks.getLength()) {
                Task task = tasks.deleteTask(0);
                response = "Noted. I've removed this task:\n" + task + "\nNow you have " + tasks.getLength() + " tasks in the list.";
                storage.updateFile(tasks);
            } else {
                throw new DukeException("☹ OOPS!!! Input a valid index"); 
            }
        } else {
            throw new DukeException("☹ OOPS!!! Input a valid index");
        }
        return response;
    }
}
