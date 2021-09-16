package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Task;
import duke.task.ToDo;

/**
 * Encapsulates the add task command.
 */
public class AddTodoCommand implements Command {
    private String todoDesc;

    /**
     * Constructor for an AddToDoCommand instance.
     *
     * @param todoDesc Todo description as entered by user.
     */
    public AddTodoCommand(String todoDesc) {
        this.todoDesc = todoDesc;
    }

    /**
     * Creates a new task from user's input and adds task to the given task list.
     * @param tasks TaskList instance which the new task is to be added to.
     * @param ui Duke's UI.
     * @return The String representation of Duke's response.
     * @throws DukeException For invalid inputs.
     */
    @Override
    public String execute(TaskList tasks, Ui ui) throws DukeException {
        Task t = new ToDo(todoDesc);
        tasks.add(t);
        return ui.formatDoneAddingTaskMsg(tasks, t);
    }

    /**
     * Indicates if the command is an exit command.
     *
     * @return If the command is an exit command.
     */
    public boolean isExit() {
        return false;
    }
}
