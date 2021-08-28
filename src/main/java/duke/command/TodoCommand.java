package duke.command;

import java.io.FileNotFoundException;

import duke.Storage;
import duke.UI;
import duke.task.TaskList;

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
