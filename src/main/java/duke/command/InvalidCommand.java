package duke.command;

import duke.task.TaskList;

import duke.Storage;

import duke.exception.DukeException;
import duke.exception.InvalidCommandException;

/**
 * Represents the user command when the user enters an invalid command.
 */
public class InvalidCommand extends Command {
    private String command;

    /**
     * Constructor for the InvalidCommand class where the user command is initialized.
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
     * Prints an error message when the user enters an invalid command.
     *
     * @param taskList TaskList that stores the tasks.
     * @param storage Storage that deals with loading tasks from the file and saving tasks in the file.            
     */
    public void execute(TaskList taskList, Storage storage) {
        DukeException exp = new InvalidCommandException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        System.out.println(exp);
    }
}
