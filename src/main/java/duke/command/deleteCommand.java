package duke.command;

import duke.task.Task;
import duke.task.TaskList;

import duke.Storage;

import duke.Ui;

/**
 * Represents the user command when the user is deletes a task.
 */
public class deleteCommand extends Command {
    private String command;

    /**
     * Constructor for the deleteCommand class where the user command is initialized.
     *
     * @param command Command entered by the user.
     */
    public deleteCommand(String command) {
        super(command);
        this.command = command;
    }

    /**
     * Returns the string representation of the class.
     *
     * @return String description of the class.
     */
    public String toString() {
        return "This is a delete command";
    }

    /**
     * Executes the response when the user enters a 'delete' command and updates the task list and storage 
     * file (duke.txt)
     *
     * @param taskList TaskList that stores the tasks.
     * @param storage Storage that deals with loading tasks from the file and saving tasks in the file.            
     */
    public void execute(TaskList taskList, Storage storage) {
        int value = Integer.parseInt(command.replaceAll("[^0-9]", ""));
        Task task = taskList.getTask(value-1);
        taskList.removeTask(value-1);
        Ui.deleteResponse(task);
        storage.writeToFile("./duke.txt", taskList);
    }
}
