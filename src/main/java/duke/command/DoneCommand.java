package duke.command;

import duke.Duke;
import duke.Response;

/**
 * Represents a command for which marks a task in the main dukelist as
 * completed.
 */
public class DoneCommand implements Command {
    @Override
    public void exec(String args) {
        int i = 0;
        try {
            i = Integer.parseInt(args);
        } catch (NumberFormatException exception) {
            new Response("Invalid Input! Please enter an integer...").print();
            return;
        }
        Duke.getList().markCompleted(i).print();
    }

    @Override
    public String getLabel() {
        return "done";
    }
}