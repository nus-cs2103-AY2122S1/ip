package duke.command;

import java.util.ArrayList;

import duke.error.DukeException;
import duke.general.Storage;
import duke.general.Tasklist;
import duke.general.Ui;
import duke.task.Task;


/**
 * Command for the program to mark a task as completed
 */
public class DoneCommand extends Command implements Revertible {
    private String[] input;
    private Task task;
    private ArrayList<Task> state;

    /**
     * Constructor for the DoneCommand object
     * @param input Input string array by the user
     */
    public DoneCommand(String[] input) {
        assert(input != null) : "Input into command was null!";
        this.input = input;
    }

    @Override
    public String execute(Tasklist tasks, Storage storage, Ui ui) throws DukeException {
        // save current state before execution
        this.state = tasks.copyList();

        Task t = tasks.doneTask(input);
        task = t;
        storage.updateSave(tasks.getList());
        tasks.addHistory(this);
        return ui.doneResponse(t);
    }

    @Override
    public String revert(Tasklist tasks, Storage storage, Ui ui) throws DukeException {
        tasks.replaceList(this.state);
        storage.updateSave(tasks.getList());
        return "Successfully undo done on: " + task;
    }
}
