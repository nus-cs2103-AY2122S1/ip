package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;

public class TodoCommand extends Command {

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList taskList, String input, Storage storage) throws DukeException {
        try {
            if (input.replaceAll("\\s", "").length() == 4) {
                throw new DukeException(DukeException.Type.EmptyTodo);
            } else {
                Todo t = new Todo(input.substring(5));
                storage.addTask(t);
                taskList.addTask(t);
                return Ui.printTaskCreated(t, taskList.getNumTasks());
            }
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
