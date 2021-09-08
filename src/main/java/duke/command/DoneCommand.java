package duke.command;

import duke.error.DukeException;
import duke.general.Storage;
import duke.general.Tasklist;
import duke.general.Ui;
import duke.task.Task;

import java.util.ArrayList;

/**
 * Command for the program to mark a task as completed
 */
public class DoneCommand extends Command implements Revertible {
    private String[] input;

    public DoneCommand(String[] input) {
        assert(input != null) : "Input into command was null!";
        this.input = input;
    }

    @Override
    public String execute(Tasklist tasks, Storage storage, Ui ui) throws DukeException {
        Task t = tasks.doneTask(input);
        if (t != null) {
            storage.modifySave(tasks.getList());
            tasks.addHistory(this);
            return ui.doneResponse(t);
        }
        return "";
    }

    @Override
    public String revert(Tasklist tasks, Storage storage, Ui ui) throws DukeException {
        tasks.doneTask(input);
        storage.modifySave(tasks.getList());
        return "Successfully undo done!";
    }
}
