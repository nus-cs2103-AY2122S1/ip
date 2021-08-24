package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.command.Command;
import duke.dukeexception.NoListException;

public class Done extends Command {
    private int index;

    public Done(int index) {
        this.index = index;
    }

    public void exec(TaskList tasks, Ui ui, Storage storage) throws NoListException {
        try {
            tasks.get(this.index - 1).markAsDone();
            storage.save(tasks);
        } catch (NoListException e) {
            throw e;
        }
    }
}
