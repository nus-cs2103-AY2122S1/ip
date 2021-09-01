package duke.command;

import duke.error.DukeException;
import duke.general.Storage;
import duke.general.Tasklist;
import duke.general.Ui;
import duke.task.Task;

/**
 * Command for the program to delete a task
 */
public class DeleteCommand extends Command {
    private String[] input;

    public DeleteCommand(String[] input) {
        this.input = input;
    }

    @Override
    public String execute(Tasklist tasks, Storage storage, Ui ui) throws DukeException {
        Task t = tasks.deleteTask(this.input);
        if (t != null) {
            storage.modifySave(tasks.getList());
            return ui.deleteResponse(t, tasks);
        }
        return "";
    }
}
