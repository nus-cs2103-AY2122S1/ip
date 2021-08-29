package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;

/**
 * Contains the executables when the user uses the 'Todo' command.
 */
public class ToDoCommand extends Command {

    private String task;

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
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (task.length() == 0) {
            ui.showError("OOPS!!! The description of a todo cannot be empty.\n");
            return;
        }
        Todo taskToDo = new Todo(task);
        taskList.add(taskToDo);
        storage.save(taskList);
        ui.addMessage();
        ui.showTask(taskToDo);
        ui.showListLength(taskList);

    }
}
