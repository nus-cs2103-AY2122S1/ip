package command;

import bot.DukeException;
import bot.Storage;
import bot.TaskList;
import bot.UserInterface;
import task.Task;

public class DoneCommand extends Command {

    public DoneCommand(String input) {
        super(input);
    }

    public void execute(TaskList list, UserInterface ui) throws DukeException {
        try {

            int index = Integer.parseInt(input.substring(5)) - 1;
            Task newTask = list.getTask(index);
            newTask.markAsDone();
            list.setTask(index, newTask);
            Storage.save(list);

            System.out.println("-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~\n"
                    + "Great! I've marked the following task as done:\n"
                    + list.getTask(index).getTaskState()
                    + "\n"
                    + "-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~\n");

        } catch (NumberFormatException e) {
            throw new DukeException("It looks like you did not enter a valid integer for the \"done\" command. Please try again!");
        }
    }
}