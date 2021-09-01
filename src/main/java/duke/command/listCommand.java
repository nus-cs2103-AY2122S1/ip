package duke.command;

import duke.task.TaskList;

import duke.Storage;

/**
 * Represents the user command when the user wants to view the task list.
 */
public class listCommand extends Command {
    private String command;

    /**
     * Constructor for the listCommand class where the user command is initialized.
     *
     * @param command Command entered by the user.
     */
    public listCommand(String command) {
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
     * Displays the task list when the user enters 'list'
     *
     * @param taskList TaskList that stores the tasks.
     * @param storage Storage that deals with loading tasks from the file and saving tasks in the file.            
     */
    public void execute(TaskList taskList, Storage storage) {
        taskList.printList();
    }
}
