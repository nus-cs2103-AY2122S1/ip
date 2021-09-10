package command;

import bot.DukeException;
import bot.TaskList;
import bot.UserInterface;

/**
 * A class that encapsulates a List Command given to Duke.
 */
public class ListCommand extends Command {

    /**
     * Constructor for the ListCommand class.
     *
     * @param input The input given by the user.
     */
    public ListCommand(String input) {
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
    public String execute(TaskList list, UserInterface ui) {

        String tasks = "";
        for (int i = 0; i < list.getSize(); i++) {
            tasks = tasks
                    + String.valueOf(i + 1)
                    + ". "
                    + list.getTask(i).getTaskState()
                    + "\n";
        }

        return ui.showTaskList(tasks);
    }
}
