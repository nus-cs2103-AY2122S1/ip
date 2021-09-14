package duke.command;

import java.util.ArrayList;

import duke.error.DukeException;
import duke.general.Storage;
import duke.general.TaskType;
import duke.general.Tasklist;
import duke.general.Ui;
import duke.task.Task;

/**
 * Command for the program to add a task
 */
public class AddCommand extends Command implements Revertible {
    private TaskType type;
    private String[] input;
    private ArrayList<Task> state;
    private Task task;

    /**
     * Constructs the AddCommand object
     * @param t Task type
     * @param inputSplit Input by user
     */
    public AddCommand(TaskType t, String[] inputSplit) {
        this.type = t;
        this.input = inputSplit;
    }

    @Override
    public String execute(Tasklist tasks, Storage storage, Ui ui) throws DukeException {
        // save state before execution
        this.state = tasks.copyList();

        // main execution
        Task t = tasks.addTask(this.type, this.input);
        task = t;
        storage.appendSave(t);
        tasks.addHistory(this);
        return ui.addResponse(t, tasks.size());
    }

    @Override
    public String revert(Tasklist tasks, Storage storage, Ui ui) throws DukeException {
        // replace with previous state
        tasks.replaceList(this.state);
        storage.updateSave(tasks.getList());
        return "Successfully undone add on: " + task;
    }
}
