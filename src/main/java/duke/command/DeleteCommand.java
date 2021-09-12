package duke.command;

import duke.DukeException;
import duke.Task;
import duke.TaskList;

public class DeleteCommand extends Command {

    private final TaskList dukeList;

    public DeleteCommand(TaskList dukeList) {
        this.dukeList = dukeList;
    }

    @Override
    public String getResponse(String input) {
        int itemNumber = 0;
        if (input.split(" ", 2).length == 1) {
            return "Oops! Looks like you are missing the number of the task you wish to delete! "
                    + "Try again :-)";
        }
        String numberInput = input.split(" ", 2)[1];
        try {
            itemNumber = Integer.parseInt(numberInput);
        } catch (NumberFormatException e) {
            return "You may have entered something incorrectly. Try adding a number behind 'delete'!";
        }
        String message = "Oops! You may have incorrectly entered a number. Try again!";
        if (itemNumber > dukeList.getSize()) {
            return "Oops! Looks like you are trying to delete something that is not in your list! "
                    + "Try again!";
        }
        Task removed = dukeList.removeTask(itemNumber - 1);
        try {
            dukeList.update();
        } catch (DukeException e) {
            return "Oops!, we are unable to update your list! Try again!";
        }
        return "Noted. I've removed this task:\n"
                + removed.toString()
                + "\nNow you have "
                + (dukeList.getSize())
                + " tasks in the list";

    }
}
