package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Todo;

public class TodoCommand extends Command{
    private final Todo todo;

    public TodoCommand(String description) throws DukeException {
        if (description.trim().equals("")) {
            throw new DukeException("Todos can't be empty!");
        }
        this.todo = new Todo(description.trim());
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.addTask(todo);
        storage.write(taskList);
        ui.showTaskAdded(todo, taskList);
    }

}
