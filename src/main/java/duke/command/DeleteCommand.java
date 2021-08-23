package duke.command;

import duke.DukeList;
import duke.exception.InvalidArgumentException;

/**
 * Represents a command that removes a task from the main DukeList.
 */
public class DeleteCommand implements Command {
    private DukeList duke;

    public DeleteCommand(DukeList duke) {
        this.duke = duke;
    }

    @Override
    public void exec(String args) {
        int i = 0;
        try {
            i = Integer.parseInt(args);
        } catch (NumberFormatException exception) {
            throw new InvalidArgumentException("Invalid Input! Please enter an integer...");
        }
        this.duke.deleteWithResponse(i).print();
    }

    @Override
    public String getLabel() {
        return "delete";
    }

}
