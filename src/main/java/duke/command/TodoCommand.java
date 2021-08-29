package duke.command;

import java.io.FileNotFoundException;

import duke.ResponseLogic;
import duke.Storage;
import duke.task.TaskList;

public class TodoCommand extends Command {

    private String description;

    public TodoCommand(String description) {
        this.description = description;
    }

    @Override
    public String execute(TaskList tasks, ResponseLogic responseLogic, Storage storage) throws FileNotFoundException {
        return tasks.addTodo(description, storage, responseLogic);
    }
}
