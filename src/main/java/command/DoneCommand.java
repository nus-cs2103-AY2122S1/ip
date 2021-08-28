package main.java.command;

import main.java.bot.DukeException;
import main.java.bot.Storage;
import main.java.bot.TaskList;
import main.java.bot.UserInterface;
import main.java.task.Task;

/**
 * A class that encapsulates a Done Command given to Duke.
 */
public class DoneCommand extends Command {

    /**
     * Constructor for the DoneCommand class.
     *
     * @param input The input given by the user.
     */
    public DoneCommand(String input) {
        super(input);
    }

    /**
     * Executes the done command with the given input.
     *
     * @param list The list of tasks to be modified by the command.
     * @param ui The UI of Duke to be invoked by the command.
     * @throws DukeException if the input given is not of the correct format.
     */
    public void execute(TaskList list, UserInterface ui) throws DukeException {
        try {

            int index = Integer.parseInt(input.substring(5)) - 1;
            Task newTask = list.getTask(index);
            newTask.markAsDone();
            list.setTask(index, newTask);
            Storage.save(list);

            System.out.println("-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~\n"
                    + "Great! I've marked the following main.java.task as done:\n"
                    + list.getTask(index).getTaskState()
                    + "\n"
                    + "-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~\n");

        } catch (NumberFormatException e) {
            throw new DukeException("It looks like you did not enter a valid integer for the \"done\" main.java.command. Please try again!");
        }
    }
}