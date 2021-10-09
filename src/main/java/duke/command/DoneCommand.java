package duke.command;

import duke.exception.DukeException;
import duke.exception.EmptyDescriptionException;
import duke.exception.TaskNotFoundException;
import duke.task.Task;
import duke.task.TaskList;
import duke.Storage;
import duke.Ui;

/**
 * Represents the user command when the user is done with a task in the task list.
 */
public class DoneCommand extends Command {
    private String command;

    /**
     * Represents a constructor for the DoneCommand class where the user command is initialized.
     *
     * @param command Command entered by the user.
     */
    public DoneCommand(String command) {
        super(command);
        this.command = command;
    }

    /**
     * Returns the string representation of the class.
     *
     * @return String description of the class.
     */
    public String toString() {
        return "This is a done command";
    }

    /**
     * Executes the response when the user enters a done command and updates the task list and storage 
     * file (duke.txt)
     *
     * @param taskList TaskList that stores the tasks.
     * @param storage Storage that deals with loading tasks from the file and saving tasks in the file.
     * @return String representation of the task that is done by the user.
     * @throws DukeException If user doesn't provide a description for the command or tries to access a task 
     * that doesn't exist.
     */
    public String execute(TaskList taskList, Storage storage) throws DukeException {
        if (command.trim().length() <= 4) {
            throw new EmptyDescriptionException();
        }
        
        int value = Integer.parseInt(command.replaceAll("[^0-9]", ""));
        if (value > taskList.getSize() || value < 0) {
            throw new TaskNotFoundException();
        }
        
        Task task = taskList.getTask(value - 1);
        task.markAsDone();
        assert task.getStatusIcon().equals("X"): "Since the task is marked as done, the status icon returned should be X";
        
        storage.writeToFile("./duke.txt", taskList);
        
        Ui ui = new Ui(taskList, storage);
        String response = ui.doneResponse(task);
        return response;
    }
}
