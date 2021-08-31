package duke.command;

import duke.UI;

public class ExitCommand extends Command {

    public ExitCommand() {}

    @Override
    public boolean canExit() {
        return true;
    }

    @Override
    public String getResponse(String input) {
        return UI.exit();
    }
}
