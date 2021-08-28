package main.java.command;

import main.java.bot.DukeException;
import main.java.bot.Storage;
import main.java.bot.TaskList;
import main.java.bot.UserInterface;
import main.java.task.Task;

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
