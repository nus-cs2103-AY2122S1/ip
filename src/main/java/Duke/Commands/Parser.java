package Duke.Commands;

import Duke.Ui.UserInput;

public class Parser {
    private static final Command[] COMMAND_LIST = {
            new ExitCommand(),
            new AddTaskCommand(),
            new ListTasksCommand(),
            new DoneTaskCommand(),
            new RemoveTaskCommand()
    };

    /**
     * Parses the given input and returns the matching command
     * @param input input to match against each command in the command list
     * @return the respective command object if matched, the unknown command object otherwise
     * @exception UnknownCommandException if no command matching the keyword was found
     */
    public static Command parse(UserInput input) throws UnknownCommandException {
        String keyword = input.getKeyword();
        for (Command cmd : COMMAND_LIST) {
            // Case insensitive contains
            if (cmd.getKeywords().stream().anyMatch(keyword::equalsIgnoreCase))
                return cmd;
        }

        throw new UnknownCommandException(input.getRaw());
    }
}
