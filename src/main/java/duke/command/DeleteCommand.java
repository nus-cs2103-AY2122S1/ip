package duke.command;

import duke.error.DukeException;
import duke.general.Storage;
import duke.general.TaskType;
import duke.general.Tasklist;
import duke.general.Ui;
import duke.task.Task;

import java.util.ArrayList;

/**
 * Command for the program to delete a task
 */
public class DeleteCommand extends Command implements Revertible{
    private String[] input;
    private TaskType type;
    private ArrayList<Task> state;

    public DeleteCommand(String[] input) {
        assert(input != null) : "Input into command was null!";
        this.input = input;
    }

    @Override
    public String execute(Tasklist tasks, Storage storage, Ui ui) throws DukeException {
        // save current state before execution
        this.state = tasks.copyList();

        Task t = tasks.deleteTask(this.input);
        type = t.getTaskType();
        storage.modifySave(tasks.getList());
        tasks.addHistory(this);
        return ui.deleteResponse(t, tasks);
    }

    @Override
    public String revert(Tasklist tasks, Storage storage, Ui ui) throws DukeException {
        assert(type != null) : "Tasktype of Command not initialized during execution!";
        tasks.replaceList(state);
        storage.modifySave(tasks.getList());
        return "Successfully undone delete";
    }
}
