package duke.command;

import duke.*;
import duke.io.ResponseManager;
import duke.task.Task;
import duke.task.TaskManager;

/**
 * This class encapsulates the command to add a To-Do type task.
 * It is triggered by the parser and uses the TaskManager, Ui, and Storage.
 */
public class AddToDoCommand implements ICommand {

    private String input;
    private String reply;

    /**
     * Constructor for the command.
     *
     * @param input The user's input which triggered the creation of this command.
     */
    public AddToDoCommand(String input) {
        this.input = input;
    }

    /**
     * Adds the to-do task by interacting with the relevant instances as mentioned above.
     *
     * @param tm The TaskManager object controlling the tasks in Duke.
     * @param responseManager The Ui object managing Duke's user interface.
     * @param storage The Storage object managing the local storing of tasks.
     */
    public void execute(TaskManager tm, ResponseManager responseManager, Storage storage) {
        try {
            Task addedTask = tm.addToDo(input);
            reply = responseManager.getTaskAdditionMessage(addedTask, tm.getTasks().size());
            storage.updateSave(tm.getTasks());
        } catch (DukeException.NoNameException e) {
            reply = responseManager.getErrorMessage(e);
        }
    }

    public String getReply() {
        return reply;
    }
}
