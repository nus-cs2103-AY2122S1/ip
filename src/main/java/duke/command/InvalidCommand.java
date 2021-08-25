package duke.command;

import duke.main.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

/**
 * The InvalidCommand class represents the case when the user has entered an invalid command.
 */
public class InvalidCommand extends Command {

    /**
     * Method which throws an error message indicating that the user has entered an invalid command.
     *
     * @param tasks The list of Task.
     * @param ui The Ui objects that handles input from user and output to user.
     * @param storage The Storage object that handles reading/writing of data.
     * @throws DukeException Always as user has entered invalid command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String errorMessage = "\t Sorry I do not understand this command \n";
        errorMessage += "\t Please use one of the following commands: \n";
        errorMessage += "\t \t list - To list the added tasks so far\n";
        errorMessage += "\t \t todo {description} - To add a ToDo task\n";
        errorMessage += "\t \t deadline {description} /by {time} - To add a Deadline task\n";
        errorMessage += "\t \t event {description} /at {time} - To add an Event task\n";
        errorMessage += "\t \t done {number} - To mark the indicated task as done\n";
        errorMessage += "\t \t delete {number} - To delete the indicated task\n";
        errorMessage += "\t \t bye (To exit programme)\n";
        throw new DukeException(errorMessage);
    }
}
