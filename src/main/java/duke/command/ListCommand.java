package duke.command;

import duke.task.TaskList;
import duke.Storage;

/**
 * Represents the user command when the user wants to view the task list.
 */
public class ListCommand extends Command {
    private String command;

    /**
     * Represents a constructor for the ListCommand class where the user command is initialized.
     *
     * @param command Command entered by the user.
     */
    public ListCommand(String command) {
        super(command);
        this.command = command;
    }

    /**
     * Returns the string representation of the class.
     *
     * @return String description of the class.
     */
    public String toString() {
        return "This is a list command";
    }

    /**
     * Returns the task list when the user enters the list command.
     * 
     * @param taskList TaskList that stores the tasks.
     * @param storage Storage that deals with loading tasks from the file and saving tasks in the file.
     * @return String representation of the task list.
     */
    public String execute(TaskList taskList, Storage storage) {
        String response = taskList.printList();
        return response;
    }
}
