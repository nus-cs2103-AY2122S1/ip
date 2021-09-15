package duke.command;

import duke.Ui;
import duke.task.TaskList;
import duke.Storage;

/**
 * Represents the user command when the user enters an invalid command.
 */
public class InvalidCommand extends Command {
    private String command;

    /**
     * Represents a constructor for the InvalidCommand class where the user command is initialized.
     *
     * @param command Command entered by the user.
     */
    public InvalidCommand(String command) {
        super(command);
        this.command = command;
    }

    /**
     * Returns the string representation of the class.
     *
     * @return String description of the class.
     */
    public String toString() {
        return "This is an invalid command";
    }

    /**
     * Returns an error message when the user enters an invalid command.
     *
     * @param taskList TaskList that stores the tasks.
     * @param storage Storage that deals with loading tasks from the file and saving tasks in the file.            
     */
    public String execute(TaskList taskList, Storage storage) {
        String response = "OOPS!!! I'm sorry, but I don't know what that means :-(";
        Ui.printInput(response);
        return response;
    }
}
