package duke.command;

import java.io.FileNotFoundException;

import duke.ResponseLogic;
import duke.Storage;
import duke.task.TaskList;

/** The Command class responsible for creating a task that has to be done. */
public class TodoCommand extends Command {

    private String description;

    /**
     * Initializes the description of the task.
     *
     * @param description The description of the task.
     */
    public TodoCommand(String description) {
        this.description = description;
    }

    @Override
    public String execute(TaskList tasks, ResponseLogic responseLogic, Storage storage) throws FileNotFoundException {
        return tasks.addTodo(description, storage, responseLogic);
    }
}
