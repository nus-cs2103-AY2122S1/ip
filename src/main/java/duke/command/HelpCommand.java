package duke.command;

import duke.Duke;
import duke.DukeException;
import duke.Parser;
import duke.ui.Ui;

public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "help";

    /**
     * Returns the command word for a help command.
     *
     * @return "help" representing a help command.
     */
    @Override
    public String getCommandWord() {
        return COMMAND_WORD;
    }

    @Override
    public String run(Duke duke, Parser parser) throws DukeException {
        return Ui.helpMessage();
    }
}
