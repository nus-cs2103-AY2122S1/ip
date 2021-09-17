package command;

import bot.DukeException;
import bot.TaskList;
import bot.UserInterface;
import task.Task;

/**
 * A class that encapsulates a Find Command given to Duke.
 */
public class FindCommand extends Command {

    /**
     * Constructor for the FindCommand class.
     *
     * @param input The input given by the user.
     */
    public FindCommand(String input) {
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
        if (input.trim().length() == 4) {
            throw new DukeException("You have not provided an input to search your list with. Please try again!");
        } else {
            String key = input.substring(5).trim();
            String tasks = "";
            for (int i = 0; i < list.getSize(); i++) {
                Task currentTask = list.getTask(i);
                if (currentTask.getTask().contains(key)) {
                    tasks += (i + 1)
                            + ". "
                            + currentTask.getTaskState()
                            + "\n";
                }
            }
            return ui.showTaskFound(tasks);
        }
    }
}
