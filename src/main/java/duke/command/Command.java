package duke.command;

import duke.Duke;
import duke.Parser;
import duke.DukeException;

public abstract class Command {
    public static final Command[] COMMANDS = new Command[] {
            new DeadlineCommand(), new DeleteCommand(), new DoneCommand(), new EndCommand(),
            new EventCommand(), new ListCommand(), new TodoCommand()
    };

    abstract public String getCommandWord();

    abstract public void run(Duke duke, Parser parser) throws DukeException;

    public static Command identifyCommand(String input) throws DukeException {
        for (Command c : COMMANDS) {
            if (c.getCommandWord().equals(input)) {
                return c;
            }
        }
        throw new DukeException("Sorry, I don't understand this command");
    }
}
