package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Todo;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.io.IOException;

/**
 * The DeadlineCommand class encapsulates the execution of the todo command from the user.
 */
public class TodoCommand extends Command {
    /** The todo command inputted by the user. */
    String fullCommand;

    /**
     * Constructor to intialise a TodoCommand.
     * @param fullCommand The todo command inputted by the user.
     */
    public TodoCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * Executes the response to the todo command from the user.
     * @param storage The storage Duke uses to save and load the tasklist from.
     * @param taskList The list of tasks Duke needs to execute on.
     * @param ui The Ui Duke utilises to interact with the user.
     */
    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) throws IOException, DukeException {
        try {
            if (fullCommand.equals("todo")) {
                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
            }

            String todoDescription = fullCommand.split(" ", 2)[1];
            Todo todo = new Todo(todoDescription);
            taskList.storeTask(todo);
            storage.saveFile(taskList.getAllTasks());
            ui.showTaskAdded(todo, taskList);

        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
    }
}
