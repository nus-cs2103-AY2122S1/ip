package duke;

import duke.command.*;

class Parser {
    // constants for special commands
    private static final String CMD_BYE = "bye";
    private static final String CMD_LIST = "list";
    private static final String CMD_DONE = "done";
    private static final String CMD_TODO = "todo";
    private static final String CMD_DDL = "deadline";
    private static final String CMD_EVENT = "event";
    private static final String CMD_DEL = "delete";
    private static final String CMD_FIND = "find";
    private static final String CMD_ARCH = "archive";
    private static final String CMD_UnARC = "unarchive";
    private static final String CMD_ARCH_LIST = "list-archive";

    /**
     * Parses the given command to determine what to execute next.
     * @param fullCommand the String command.
     * @return a Command instance.
     * @throws DukeException When the command keyword cannot be understood.
     */
    public static Command parse(String fullCommand) throws DukeException {
        int commandLen = fullCommand.length();
        assert commandLen > 0 : "The command should not be empty.";

        String keywork = fullCommand.split(" ")[0];

        switch (keywork) {
        case CMD_LIST:
            return new DisplayListCommand(true);
        case CMD_ARCH_LIST:
            return new DisplayListCommand(false);

        case CMD_DONE:
            return new DoneCommand(fullCommand);
        case CMD_DEL:
            return new DeleteCommand(fullCommand);
        case CMD_ARCH:
            return new ArchiveCommand(fullCommand, true);
        case CMD_UnARC:
            return new ArchiveCommand(fullCommand, false);

        case CMD_TODO:
            return new AddCommand(fullCommand, TaskEnum.TODO);
        case CMD_DDL:
            return new AddCommand(fullCommand, TaskEnum.DDL);
        case CMD_EVENT:
            return new AddCommand(fullCommand, TaskEnum.EVENT);
        case CMD_BYE:
            return new ExitCommand();
        case CMD_FIND:
            return new FindCommand(fullCommand);
        default:
            throw new DukeException("Yiyang-bot doesn't understand that command.");
        }
    }
}
