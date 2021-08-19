package Duke.Commands;

import Duke.Duke;
import Duke.DukeException;

import java.util.Set;

public abstract class Command {
    private static final Command[] COMMAND_LIST = {
            new ExitCommand(),
            new AddTaskCommand(),
            new ListTasksCommand(),
            new DoneTaskCommand()
    };

    /**
     * Factory method
     * @param input input to match against each command in the command list
     * @return the respective command object if matched, the unknown command object otherwise
     * @exception UnknownCommandException if no command matching the keyword was found
     */
    public static Command matching(Duke.UserInput input) throws UnknownCommandException {
        String keyword = input.getKeyword().toLowerCase();
        for (Command cmd : COMMAND_LIST) {
            if (cmd.getKeywords().contains(keyword))
                return cmd;
        }

        throw new UnknownCommandException(input.getRaw());
    }

    abstract public void run(Duke duke, Duke.UserInput input) throws DukeException;

    abstract protected Set<String> getKeywords();
}
