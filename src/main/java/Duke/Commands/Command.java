package Duke.Commands;

import Duke.Duke;
import Duke.DukeException;
import Duke.Task.InvalidTaskException;

import java.util.Set;

public abstract class Command {
    private static final Command[] COMMAND_LIST = {
            new ExitCommand(),
            new AddTaskCommand(),
            new ListTasksCommand(),
            new DoneTaskCommand(),
            new RemoveTaskCommand()
    };

    private static final String INVALID_TASK_NUMBER_FORMAT_MESSAGE = "\"%s\" is not a valid task number.";
    protected static final String TASKS_COUNT_MESSAGE = "Now you have %d tasks in the list.";

    /**
     * Factory method
     * @param input input to match against each command in the command list
     * @return the respective command object if matched, the unknown command object otherwise
     * @exception UnknownCommandException if no command matching the keyword was found
     */
    public static Command matching(Duke.UserInput input) throws UnknownCommandException {
        String keyword = input.getKeyword();
        for (Command cmd : COMMAND_LIST) {
            // Case insensitive contains
            if (cmd.getKeywords().stream().anyMatch(keyword::equalsIgnoreCase))
                return cmd;
        }

        throw new UnknownCommandException(input.getRaw());
    }

    abstract public void run(Duke duke, Duke.UserInput input) throws DukeException;

    abstract protected Set<String> getKeywords();

    protected static int parseTaskNumber(Duke.UserInput input) throws InvalidTaskException {
        String args = input.getArgs();
        try {
            return Integer.parseInt(args);
        } catch (NumberFormatException e) {
            throw new InvalidTaskException(String.format(INVALID_TASK_NUMBER_FORMAT_MESSAGE, args));
        }
    }
}
