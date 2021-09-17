package command;

import bot.DukeException;
import bot.Storage;
import bot.TaskList;
import bot.UserInterface;
import task.Task;

/**
 * A class that encapsulates a Delete Command given to Duke.
 */
public class DeleteCommand extends Command {

    /**
     * Constructor for the DeleteCommand class.
     *
     * @param input The input given by the user.
     */
    public DeleteCommand(String input) {
        super(input);
    }

    /**
     * Returns the proper response according to the given input.
     *
     * @param list The list of tasks to be modified by the command.
     * @param ui The UI of Duke to be invoked by the command.
     * @return A String representation of Duke's response according to the input given by the user.
     * @throws DukeException if the input given is not of the correct format.
     */
    @Override
    public String execute(TaskList list, UserInterface ui) throws DukeException {
        try {
            if (input.trim().length() == 6) {
                throw new DukeException(
                        "It looks like you did not enter a valid integer for the \"delete\" command. Please try again!");
            }
            int index = Integer.parseInt(input.trim().substring(7)) - 1;
            if (index >= list.getSize() || index < 0) {
                throw new DukeException("That task doesn't exist. Please try again!");
            }
            Task removedTask = list.getTask(index);
            list.removeTask(index);
            Storage.save(list);
            return ui.showTaskDeleted("" + (index + 1) + ". " + removedTask.getTaskState());
        } catch (NumberFormatException e) {
            throw new DukeException(
                    "It looks like you did not enter a valid integer for the \"delete\" command. Please try again!");
        }
    }
}
