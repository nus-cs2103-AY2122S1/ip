package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class DeleteCommand extends Command {

    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (index > tasks.getSize()) {
            throw new DukeException("This entry does not exist!");
        }
        Task deletedTask = tasks.getTask(index);
        tasks.deleteTask(index);
        ui.printTemplate( " Noted. I've removed this task:\n" + "  " + deletedTask + System.lineSeparator() + " Now you have " + tasks.getSize() + " tasks in the list.");
        storage.update(tasks);
    }
}
