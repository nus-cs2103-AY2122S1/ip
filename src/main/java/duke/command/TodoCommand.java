package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import duke.Storage;
import duke.exception.DukeException;
import duke.exception.EmptyDescriptionException;
import duke.Ui;

/**
 * Represents the user command when the user enters a todo task.
 */
public class TodoCommand extends Command {
    private String command;

    /**
     * Represents a constructor for the TodoCommand class where the user command is initialized.
     *
     * @param command Command entered by the user.
     */
    public TodoCommand(String command) {
        super(command);
        this.command = command;
    }

    /**
     * Returns the string representation of the class.
     *
     * @return String description of the class.
     */
    public String toString() {
        return "This is a todo command";
    }

    /**
     * Executes the response when the user enters a todo command and updates the task list and storage 
     * file (duke.txt)
     *
     * @param taskList TaskList that stores the tasks.
     * @param storage Storage that deals with loading tasks from the file and saving tasks in the file.
     * @return String representation of the new todo task as well as the number of tasks in the task list.
     * @throws DukeException If user doesn't provide a description for the todo command.
     */
    public String execute(TaskList taskList, Storage storage) throws EmptyDescriptionException {
        if (command.trim().length() <= 4) {
            throw new EmptyDescriptionException();
        } 
        
        Task task = new Todo(command.substring(5));
        taskList.addTask(task);
        assert taskList.getTask(taskList.getSize() - 1).equals(task): "last element in the task list should be " 
                + "equivalent to the most recently added task";
        
        storage.writeToFile("./duke.txt", taskList);
        
        Ui ui = new Ui(taskList, storage);
        String response = ui.taskResponse(task);
        return response;
    }
}
