package commands;

import ui.Ui;
import duke.DukeException;
import storage.Storage;
import task.TaskList;
import task.Task;

public class DeleteCommand extends Command{
    private String commands;

    public DeleteCommand(String commands) {
        this.commands = commands;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (commands.matches("\\d+")) {
            int index = Integer.parseInt(commands) - 1;
            if (index >= 0 && index < tasks.getLength()) {
                Task task = tasks.deleteTask(0);
                ui.printOutput("Noted. I've removed this task:\n" + task + "\nNow you have " + tasks.getLength() + " tasks in the list.");
                storage.updateFile(tasks);
            } else {
                throw new DukeException("☹ OOPS!!! Input a valid index"); 
            }
        } else {
            throw new DukeException("☹ OOPS!!! Input a valid index");
        }
    }
}
