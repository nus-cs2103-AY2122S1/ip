package main.java.command;

import main.java.bot.DukeException;
import main.java.bot.TaskList;
import main.java.bot.UserInterface;
import main.java.task.Task;

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
     * Executes the find command with the given input.
     *
     * @param list The list of tasks to be modified by the command.
     * @param ui The UI of Duke to be invoked by the command.
     * @throws DukeException if the input given is not of the correct format.
     */
    public void execute(TaskList list, UserInterface ui) throws DukeException {

        String key = input.substring(5);
        if (key.length() == 0) {
            throw new DukeException("You have not provided an input to search your list with. Please try again!");
        } else {
            System.out.println("-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~\nHere are the matching tasks in your list:\n");
            int index = 1;
            for (int i = 0; i < list.getSize(); i++) {
                Task currentTask = list.getTask(i);
                if (currentTask.getTask().contains(key)) {
                    System.out.println(index
                            + ". "
                            + currentTask.getTaskState()
                            + "\n");
                }
            }
            System.out.println("Hope you found what you were looking for!\n-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~");
        }
    }
}
