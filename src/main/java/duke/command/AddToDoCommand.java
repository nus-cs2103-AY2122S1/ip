package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskType;
import duke.task.ToDo;

import java.io.IOException;

/**
 * A class that contains methods to add ToDo tasks to task list and a storage file.
 */
public class AddToDoCommand extends AddCommand {
    /**
     * Initializes an instance of AddToDoComman task.
     * @param type Type of task
     * @param taskDescription Task description
     */
    public AddToDoCommand(String type, String taskDescription) {
        super(type, taskDescription);
    }

    /**
     * Executes the operation of adding a new ToDo task to the list and
     * saving it to the file.
     * @param taskList The task list
     * @param ui An object to handle i/o operations through screen
     * @param storage An object to read from and write to storage file.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            addToDoTask(taskList, ui);
            storage.saveTasksToFile(taskList);
        } catch (DukeException | IOException e) {
            ui.displayError(e.getMessage());
        }
    }

    /**
     * Creates a new ToDo task and adds it to the task list.
     * @param taskList The task list
     * @param ui An object to handle i/o operations through screen
     * @throws DukeException If a new ToDo task cannot be created
     */
    public void addToDoTask(TaskList taskList, Ui ui) throws DukeException {
        Task newToDoTask = new ToDo(TaskType.TODO, super.getTaskDescription());
        taskList.add(newToDoTask);

        String response = ui.taskAddedMessage(newToDoTask)
                + System.lineSeparator() + ui.getNumOfTasksInList(taskList);
        ui.displayResponse(response);
    }
}
