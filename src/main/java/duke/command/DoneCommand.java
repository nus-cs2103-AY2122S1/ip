package duke.command;

import duke.Task;
import duke.TaskList;

public class DoneCommand extends Command {

    private final TaskList dukeList;

    public DoneCommand(TaskList dukeList) {
        this.dukeList = dukeList;
    }

    @Override
    public String getResponse(String input) {
        int itemNumber;
        if (input.split(" ", 2).length == 1) {
            return "Oops! Looks like you are missing the task number you wish to mark as done!"
                    + " Try again :-)";
        }
        String numberInput = input.split(" ", 2)[1];
        try {
            itemNumber = Integer.parseInt(numberInput);
        } catch (NumberFormatException e) {
            return "You may have entered something incorrectly. "
                    + "Try adding a number behind 'done'!";
        }
        String message = "Oops! I cannot seem to find that task number. Try again!";
        if (dukeList.isEmpty()) {
            message = "Oops! Your list is empty! Try adding a Task first!";
        } else if (itemNumber <= dukeList.getSize()) {
            Task targetItem = dukeList.getTask(itemNumber - 1);
            targetItem.markDone();
            message = "Nice! I've marked this task as done:\n" + " " + targetItem.toString();
        }
        return message;
    }
}
