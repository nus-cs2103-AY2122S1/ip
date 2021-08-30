package duke.commands;

import duke.exceptions.UserInputError;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.util.Storage;
import duke.util.Ui;

public class AddCommand extends Command {
    private final Task.Type type;
    private final String details;

    public AddCommand(String details, Task.Type type) {
        this.type = type;
        this.details = details;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws UserInputError {
        Task newTask = Task.createTask(details, type);
        tasks.addTask(newTask);
        return ui.addTaskOutput(newTask);
    }
}
