package duke.commands;

import duke.DukeException;
import duke.DukeStorage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Todo;

public class TodoCommand extends Command {

    private String description;

    public TodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, DukeStorage storage) throws DukeException {
        Todo todo = new Todo(this.description);
        taskList.add(todo);
        ui.addedMessage(taskList, todo);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
