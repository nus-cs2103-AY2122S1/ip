package duke.command;

import duke.error.DukeException;
import duke.general.Storage;
import duke.general.Tasklist;
import duke.general.Ui;
import duke.task.Task;

public class DoneCommand extends Command {
    private String[] input;

    public DoneCommand(String[] input) {
        this.input = input;
    }

    @Override
    public void execute(Tasklist tasks, Storage storage, Ui ui) throws DukeException {
        Task t = tasks.doneTask(input);
        if (t != null) {
            storage.modifySave(tasks.getList());
            ui.doneResponse(t);
        }
    }
}
