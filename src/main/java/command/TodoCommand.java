package command;

import bot.DukeException;
import bot.Storage;
import bot.TaskList;
import bot.UserInterface;
import task.TodoTask;

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
            throw new DukeException("The description of a todo cannot be empty. Please try again!");
        }

        String newTodo = input.substring(5).trim();
        TodoTask newTask;
        if (!tags.isEmpty()) {
            newTask = new TodoTask(newTodo, tags);
        } else {
            newTask = new TodoTask(newTodo);
        }
        list.addTask(newTask);
        Storage.save(list);
        return ui.showTaskAdded(newTask, list.getSize() - 1);
    }
}
