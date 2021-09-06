package duke.command;

import java.io.IOException;

import duke.storage.Storage;
import duke.task.Todo;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * The DeadlineCommand class encapsulates the execution of the todo command from the user.
 */
public class TodoCommand extends Command {

    /** The todo command inputted by the user. */
    private String todoDescription;

    /**
     * Constructor to intialise a TodoCommand.
     *
     * @param todoDescription The todo command inputted by the user.
     */
    public TodoCommand(String todoDescription) {
        this.todoDescription = todoDescription;
    }

    /**
     * Executes the response to the todo command from the user.
     *
     * @param storage The storage Duke uses to save and load the tasklist from.
     * @param taskList The list of tasks Duke needs to execute on.
     * @param ui The Ui Duke utilises to interact with the user.
     */
    @Override
    public String execute(Storage storage, TaskList taskList, Ui ui) throws IOException {

        Todo todo = new Todo(todoDescription);
        taskList.storeTask(todo);
        storage.saveFile(taskList.getAllTasks());
        String output = ui.showTaskAdded(todo, taskList);
        return output;

    }
}
