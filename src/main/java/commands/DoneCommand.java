package commands;

import exceptions.DukeException;
import storage.Storage;
import tasks.Task;
import tasks.TaskList;
import ui.Ui;

public class DoneCommand extends Command {
    private final int index;

    public DoneCommand(int index) {
        super(false);
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (index <= 0 || index > tasks.size()) {
            throw new DukeException("Looks like there is no such task to be marked as done");
        }
        Task task = tasks.markTaskAsDone(index);
        storage.save(tasks);
        String message = String.format("Nice! I've marked this task as done:\n  %s", task);
        ui.reply(message);

    }
}
