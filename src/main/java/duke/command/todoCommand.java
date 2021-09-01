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
public class todoCommand extends Command {
    private String command;

    /**
     * Constructor for the todoCommand class where the user command is initialized.
     *
     * @param command Command entered by the user.
     */
    public todoCommand(String command) {
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
     * Executes the response when the user enters a 'todo' command and updates the task list and storage 
     * file (duke.txt)
     *
     * @param taskList TaskList that stores the tasks.
     * @param storage Storage that deals with loading tasks from the file and saving tasks in the file.            
     */
    public void execute(TaskList taskList, Storage storage) {
        if (command.length() <= 5) {
            DukeException exp = new EmptyDescriptionException("OOPS!!! The description of a todo cannot be empty.");
            System.out.println(exp);
        } else {
            Task task = new Todo(command.substring(5));
            taskList.addTask(task);
            Ui.taskResponse(task);
            storage.writeToFile("./duke.txt", taskList);
        }
    }
}
