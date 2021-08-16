package duke.command;

import duke.Duke;
import duke.exception.InvalidArgumentException;

/**
 * Represents a command that removes a task from the main DukeList.
 */
public class DeleteCommand implements Command {

    @Override
    public void exec(String args) {
        int i = 0;
        try {
            i = Integer.parseInt(args);
        } catch (NumberFormatException exception) {
            throw new InvalidArgumentException("Invalid Input! Please enter an integer...");
        }
        Duke.getList().deleteWithResponse(i).print();
    }

    @Override
    public String getLabel() {
        return "delete";
    }

}
