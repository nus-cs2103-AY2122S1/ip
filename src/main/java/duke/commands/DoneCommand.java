package duke.commands;

import duke.data.TaskList;
import duke.data.exception.InvalidIndexException;
import duke.data.task.Task;
import duke.storage.Storage;
import duke.ui.Ui;

public class DoneCommand extends Command {
    private final int index;
    
    public DoneCommand(String rest) {
        this.index = Integer.parseInt(rest.strip());
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidIndexException {
        Task t = tasks.get(index - 1);
        storage.saveData(tasks.markDone(index - 1));
        ui.print(String.format("Nice! I've marked this task as done:\n %s", t.toString()));
    }
}
