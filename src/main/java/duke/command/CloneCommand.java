package duke.command;

import duke.Storage;
import duke.exception.DukeException;
import duke.exception.EmptyDescriptionException;
import duke.exception.TaskNotFoundException;
import duke.task.Task;
import duke.task.TaskList;

public class CloneCommand extends Command {
    private String command;
    private Storage storage;
    
    /**
     * Represents a constructor for the DeadlineCommand class where the user command is initialized.
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
        return "This is an edit command";
    }

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
        
        return "Task " + value + " from the task list has been cloned!" + " Here is the latest task list: \n"
                + taskList.printList();
    }
}
