package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;

/**
 * Contains the executables when the user uses the 'Todo' command.
 *
 * @author Benjamin Lui
 */
public class ToDoCommand extends Command {

    private String task;
    private final String errorMessage = "OOPS!!! The description of a todo cannot be empty.\n";

    /**
     * Constructor for the to do command
     * @param task to do task to be added to the list.
     */
    public ToDoCommand(String task) {
        this.task = task;
    }

    /**
     * Executes the to do command.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        if (task.length() == 0) {
            return ui.showError(errorMessage);
        }
        Todo taskToDo = new Todo(task);
        if (taskList.contains(taskToDo)) {
            return ui.showError("To do event, " + taskToDo.toString() + " already exists!");
        }
        taskList.add(taskToDo);
        storage.save(taskList);
        return ui.addMessage() + ui.showTask(taskToDo) + ui.showListLength(taskList);

    }
}
