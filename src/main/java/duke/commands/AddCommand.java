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

    /**
     * Creates a new Task of one of the 3 Task.Type and update the database.
     * Throws a UserInputError if Task creation fails.
     *
     * @param tasks Current taskList.
     * @param ui User interface of Duke.
     * @return String output result of the add task command.
     * @throws UserInputError Throws error if bad user input is caught.
     */
    @Override
    public String execute(TaskList tasks, Ui ui) throws UserInputError {
        Task newTask = Task.createTask(details, type);
        tasks.addTask(newTask);
        Storage.writeDatabase(tasks);
        return ui.addTaskOutput(newTask);
    }
}
