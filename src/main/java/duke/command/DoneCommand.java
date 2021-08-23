package duke.command;

import duke.Duke;
import duke.exception.InvalidArgumentException;

/**
 * Represents a command for which marks a task in the main dukelist as
 * completed.
 */
public class DoneCommand implements Command {
    private Duke duke;

    public DoneCommand(Duke duke) {
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
        this.duke.getList().markCompleted(i).print();
    }

    @Override
    public String getLabel() {
        return "done";
    }

}