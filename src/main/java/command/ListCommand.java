package main.java.command;

import main.java.bot.DukeException;
import main.java.bot.TaskList;
import main.java.bot.UserInterface;

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
     * Executes the list command with the given input.
     *
     * @param list The list of tasks to be modified by the command.
     * @param ui The UI of Duke to be invoked by the command.
     * @throws DukeException if the input given is not of the correct format.
     */
    public void execute(TaskList list, UserInterface ui) {

        String tasks = "";
        for (int i = 0; i < list.getSize(); i++) {
            tasks = tasks
                    + String.valueOf(i + 1)
                    + ". "
                    + list.getTask(i).getTaskState()
                    + "\n";
        }

        System.out.println("-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~\n"
                + tasks
                + "-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~\n");
    }
}
