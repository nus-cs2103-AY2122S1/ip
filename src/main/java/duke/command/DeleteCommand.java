package duke.command;

import java.util.ArrayList;

import duke.error.DukeException;
import duke.general.Storage;
import duke.general.Tasklist;
import duke.general.Ui;
import duke.task.Task;


/**
 * Command for the program to delete a task
 */
public class DeleteCommand extends Command implements Revertible {
    private String[] input;
    private ArrayList<Task> state;
    private Task task;

    /**
     * Constructor for the DeleteCommand object
     * @param input Input array by the user
     */
    public DeleteCommand(String[] input) {
        assert(input != null) : "Input into command was null!";
        this.input = input;
    }

    @Override
    public String execute(Tasklist tasks, Storage storage, Ui ui) throws DukeException {
        // save current state before execution
        this.state = tasks.copyList();

        Task t = tasks.deleteTask(this.input);
        task = t;
        storage.updateSave(tasks.getList());
        tasks.addHistory(this);
        return ui.deleteResponse(t, tasks);
    }

    @Override
    public String revert(Tasklist tasks, Storage storage, Ui ui) throws DukeException {
        tasks.replaceList(state);
        storage.updateSave(tasks.getList());
        return "Successfully undone delete on: " + task;
    }
}
