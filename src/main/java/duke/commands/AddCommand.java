package duke.commands;

import duke.exceptions.UserInputError;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.util.Storage;
import duke.util.Ui;

public class AddCommand extends Command {
    private final Task.Type type;
    private final String details;

    /**
     * Constructor for user add commands.
     *
     * @param details Description of the task.
     * @param type Type of task.
     */
    public AddCommand(String details, Task.Type type) {
        this.type = type;
        this.details = details;
    }

    @Override
    public String execute(TaskList tasks, Ui ui) throws UserInputError {
        Task newTask = Task.createTask(details, type);
        tasks.addTask(newTask);
        Storage.writeDatabase(tasks);
        return ui.addTaskOutput(newTask);
    }
}
