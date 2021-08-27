package duke.command;

import duke.Duke;
import duke.Parser;

public class EndCommand extends Command {
    public static final String COMMAND_WORD = "bye";

    /**
     * Returns the command word for an end command.
     *
     * @return "bye" representing an end command.
     */
    @Override
    public String getCommandWord() {
        return COMMAND_WORD;
    }

    /**
     * Stops the Duke instance.
     *
     * @param duke   Duke instance that the command is called from.
     * @param parser Parser with the user's input
     */
    @Override
    public void run(Duke duke, Parser parser) {
        duke.stop();
    }
}
