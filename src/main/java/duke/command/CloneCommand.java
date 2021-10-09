package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.exception.EmptyDescriptionException;
import duke.exception.TaskNotFoundException;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents the user command when the user clones a task in the task list.
 */
public class CloneCommand extends Command {
    private String command;
    private Storage storage;
    
    /**
     * Represents a constructor for the CloneCommand class where the user command is initialized.
     *
     * @param command Command entered by the user.
     */
    public CloneCommand(String command) {
        super(command);
        this.command = command;
    }

    /**
     * Returns the string representation of the class.
     *
     * @return String description of the class.
     */
    public String toString() {
        return "This is a clone command";
    }

    /**
     * Executes the response when the user clones a task in the task list.
     *
     * @param taskList TaskList that stores the tasks.
     * @param storage Storage that deals with loading tasks from the file and saving tasks in the file.
     * @return String representation of the updated task list.
     * @throws DukeException If command doesn't have a description or is in invalid format.
     */
    public String execute(TaskList taskList, Storage storage) throws DukeException {
        if (command.trim().length() <= 5) {
            throw new EmptyDescriptionException();
        }

        int value = Integer.parseInt(command.replaceAll("[^0-9]", ""));
        if (value > taskList.getSize() || value < 0) {
            throw new TaskNotFoundException();
        }

        Task task = taskList.getTask(value - 1);
        taskList.addTask(task);
        storage.writeToFile("./duke.txt", taskList);
        
        Ui ui = new Ui(taskList, storage);
        return ui.cloneResponse(value);
    }
}
