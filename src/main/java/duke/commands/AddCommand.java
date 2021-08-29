package duke.commands;

import duke.Storage;
import duke.Tasklist;
import duke.Ui;
import duke.tasks.Task;

import java.io.IOException;

public class AddCommand extends Command {

    private Task toAdd;

    public AddCommand(Task toAdd) {
        this.toAdd = toAdd;
    }

    @Override
    public void execute(Tasklist tasklist, Ui ui, Storage storage) throws IOException {
        try {
            tasklist.addTask(toAdd);
            ui.showAddMessage(toAdd, tasklist);
            storage.writeToFile(tasklist);
        } catch (IOException e) {
            throw new IOException();
        }
    }

}