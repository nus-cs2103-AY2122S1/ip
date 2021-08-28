package main.java.command;

import main.java.bot.DukeException;
import main.java.bot.Storage;
import main.java.bot.TaskList;
import main.java.bot.UserInterface;
import main.java.task.Task;

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
     * Executes the delete command with the given input.
     *
     * @param list The list of tasks to be modified by the command.
     * @param ui The UI of Duke to be invoked by the command.
     * @throws DukeException if the input given is not of the correct format.
     */
    public void execute(TaskList list, UserInterface ui) throws DukeException {
        try {

            int index = Integer.parseInt(input.substring(7)) - 1;
            Task removedTask = list.getTask(index);
            list.removeTask(index);
            Storage.save(list);

            System.out.println("-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~\n"
                    + "Noted. I've removed the following main.java.task:\n"
                    + (index + 1)
                    + ". "
                    + removedTask.getTaskState()
                    + "\n"
                    + "-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~\n");

        } catch (NumberFormatException e) {
            throw new DukeException("It looks like you did not enter a valid integer for the \"delete\" main.java.command. Please try again!");
        }
    }
}
