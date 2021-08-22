package duke.command;

import duke.core.Storage;
import duke.core.TaskList;
import duke.core.Ui;
import duke.exception.DukeException;

public class AddTodoCommand extends AddCommand {
    private String description;

    public AddTodoCommand(String description) {
        this.description = description;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            return tasks.recordTodo(description);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("todo should be in format: todo [DESCRIPTION]");
        }
    }
}
