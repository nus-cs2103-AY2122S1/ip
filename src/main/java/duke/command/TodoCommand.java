package duke.command;

import java.io.IOException;

import duke.storage.Storage;
import duke.task.Todo;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * The TodoCommand class encapsulates the execution of the todo command from the user.
 */
public class TodoCommand extends Command {

    /** The todo command description inputted by the user. */
    private String todoDescription;

    /**
     * Constructor to initialise a TodoCommand.
     *
     * @param todoDescription The todo command description inputted by the user.
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
     * @return The String to be printed in the Duke GUI.
     * @throws IOException If there is an exception relating to the input and output.
     */
    @Override
    public String execute(Storage storage, TaskList taskList, Ui ui) throws IOException {

        Todo todo = new Todo(todoDescription);
        taskList.storeTask(todo);
        String output = ui.showTaskAdded(todo, taskList);
        storage.saveFile(taskList.getAllTasks());
        return output;

    }
}
