package duke.command;

import duke.Duke;
import duke.exception.DukeException;

public class ExitCommand extends Command {
    public ExitCommand() {
        setCommandString("bye");
    }

    /**
     * Exits the application.
     *
     * @param input Full user input
     */
    @Override
    public void parse(String input) {
        Duke.exit();
    }
}
