package duke.command;

import duke.Duke;

public class ExitCommand extends Command {
    public ExitCommand() {
        setCommandString("bye");
    }

    @Override
    public void parse(String input) {
        Duke.exit();
    }
}
