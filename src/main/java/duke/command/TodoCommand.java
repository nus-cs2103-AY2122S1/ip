package duke.command;

import duke.Storage;
import duke.UI;
import duke.task.TaskList;

import java.io.FileNotFoundException;

public class TodoCommand extends Command {

    private String description;

    public TodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws FileNotFoundException {
        tasks.addTodo(description, storage, ui);
    }
}
