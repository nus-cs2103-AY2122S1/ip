package main.java.command;

import main.java.bot.DukeException;
import main.java.bot.Storage;
import main.java.bot.TaskList;
import main.java.bot.UserInterface;
import main.java.task.TodoTask;

/**
 * A class that encapsulates a Todo Command given to Duke.
 */
public class TodoCommand extends Command {

    /**
     * Constructor for the TodoCommand class.
     *
     * @param input The input given by the user.
     */
    public TodoCommand(String input) {
        super(input);
    }

    /**
     * Executes the todo command with the given input.
     *
     * @param list The list of tasks to be modified by the command.
     * @param ui The UI of Duke to be invoked by the command.
     * @throws DukeException if the input given is not of the correct format.
     */
    public void execute(TaskList list, UserInterface ui) throws DukeException {

        String newTask = input.substring(5);
        if (newTask.length() == 0) {
            throw new DukeException("The description of a todo cannot be empty. Please try again!");
        } else {
            list.addTask(new TodoTask(newTask));
            Storage.save(list);
            UserInterface.showTaskAdded(newTask, 1, list.getSize() - 1, "");
        }
    }
}
