package duke.commands;

import duke.Tasklist;
import duke.exceptions.NoSuchTaskException;
import duke.tasks.Task;
import duke.Ui;
import duke.Storage;

import java.io.IOException;

public class DeleteCommand extends Command {

    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(Tasklist tasklist, Ui ui, Storage storage) throws NoSuchTaskException {
        try {
            Task temp = tasklist.getTask(index);
            tasklist.deleteTask(index);
            ui.showDeleteMessage(temp, tasklist);
            storage.writeToFile(tasklist);
        } catch (NoSuchTaskException | IOException e) {
            throw new NoSuchTaskException("Task index is out of bounds");
        }

    }
}