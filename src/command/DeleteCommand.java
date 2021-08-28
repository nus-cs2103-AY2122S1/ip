package command;

import bot.DukeException;
import bot.Storage;
import bot.TaskList;
import bot.UserInterface;
import task.Task;

public class DeleteCommand extends Command {

    public DeleteCommand(String input) {
        super(input);
    }

    public void execute(TaskList list, UserInterface ui) throws DukeException {
        try {

            int index = Integer.parseInt(input.substring(7)) - 1;
            Task removedTask = list.getTask(index);
            list.removeTask(index);
            Storage.save(list);

            System.out.println("-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~\n"
                    + "Noted. I've removed the following task:\n"
                    + (index + 1)
                    + ". "
                    + removedTask.getTaskState()
                    + "\n"
                    + "-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~\n");

        } catch (NumberFormatException e) {
            throw new DukeException("It looks like you did not enter a valid integer for the \"delete\" command. Please try again!");
        }
    }
}
