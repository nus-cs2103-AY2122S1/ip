package command;

import bot.DukeException;
import bot.Storage;
import bot.TaskList;
import bot.UserInterface;
import task.TodoTask;

public class TodoCommand extends Command {

    public TodoCommand(String input) {
        super(input);
    }

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
