package Duke.Commands;

import Duke.Duke;

public abstract class Command {
    private static final Command[] COMMAND_LIST = {new ExitCommand(), new ListTodosCommand()};
    private static final Command ADD_TODO_COMMAND = new AddTodoCommand();
//    private static final Command UNKOWN_COMMAND = new UnknownCommand();

    /**
     * Factory method
     * @param input input to match against each command in the command list
     * @return the respective command object if matched, the unknown command object otherwise
     */
    public static Command matching(Duke.UserInput input) {
        for (Command cmd : COMMAND_LIST) {
            if (cmd.getKeyword().equalsIgnoreCase(input.getKeyword()))
                return cmd;
        }

        return ADD_TODO_COMMAND;
    }

    abstract public void run(Duke duke, Duke.UserInput input);

    abstract protected String getKeyword();
}
