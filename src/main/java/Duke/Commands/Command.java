package Duke.Commands;

import Duke.Duke;

abstract class Command {
    private static final Command[] COMMAND_LIST = {new ExitCommand()};
    private static final Command UNKOWN_COMMAND = new UnknownCommand();

    /**
     * Factory method
     * @param keyword string to match against each command in the command list
     * @return the respective command object if matched, the unknown command object otherwise
     */
    public static Command matching(String keyword) {
        for (Command cmd : COMMAND_LIST) {
            if (cmd.getKeyword().equalsIgnoreCase(keyword))
                return cmd;
        }

        return UNKOWN_COMMAND;
    }

    abstract public void run(Duke duke, String arg);

    abstract protected String getKeyword();
}
