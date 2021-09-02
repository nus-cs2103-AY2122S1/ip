package duke;

/**
 * A class representing a parser for commands from the CLI.
 */
public class CommandParser extends Parser<String[]> {
    private static final String[] COMMANDS_WITH_ARGS = new String[] {"todo", "deadline",
        "event", "delete", "done", "find"};
    private static final String[] COMMANDS_WITHOUT_ARGS = new String[] {"list", "bye"};
    private static final String UNKNOWN_COMMAND_MSG = "☹ OOPS!!! "
            + "I'm sorry, but I don't know what that means :-(";
    private static final String MISSING_ARGUMENT_TEMPLATE = "☹ OOPS!!! "
            + "Seems like there are missing argument(s) for %s";
    private static final String NON_NUMBER_ARGUMENT_TEMPLATE = "☹ OOPS!!! "
            + "Should have entered a number for %s, BOI";

    /**
     * Parse the given line from the CLI and returns an array of commands and arguments.
     *
     * @param cmd Line from the CLI.
     * @return An array representing the command and arguments.
     * @throws DukeException If the command is invalid or has invalid arguments.
     */
    public String[] parse(String cmd) throws DukeException {
        cmd = cmd.trim();
        boolean isInvalidCommand = true;

        String[] cmdSplit = cmd.split("[ \\t]+", 2);
        String cmdSplitFront = cmdSplit[0];
        for (String cmdCheck : COMMANDS_WITHOUT_ARGS) {
            if (cmd.equals(cmdCheck)) {
                return new String[]{cmd};
            } else if (cmdSplitFront.equals(cmdCheck)) {
                throw new DukeException("☹ OOPS!!! Unknown Argument for " + cmdCheck);
            }
        }

        for (String cmdCheck : COMMANDS_WITH_ARGS) {
            isInvalidCommand = isInvalidCommand && !(cmdSplitFront.equals(cmdCheck));
        }

        if (isInvalidCommand) {
            throw new DukeException(UNKNOWN_COMMAND_MSG);
        } else if (cmdSplit.length != 2) {
            throw new DukeException(String.format(MISSING_ARGUMENT_TEMPLATE, cmdSplitFront));
        } else if ((cmdSplitFront.equals("delete") || cmdSplitFront.equals("done"))
                && !cmdSplit[1].matches("[0-9]+")) {
            throw new DukeException(String.format(NON_NUMBER_ARGUMENT_TEMPLATE, cmdSplitFront));
        }

        switch (cmdSplitFront) {
        case "todo":
            // Fallthrough
        case "done":
            // Fallthrough
        case "delete":
            // Fallthrough
        case "find":
            return cmdSplit;
        case "deadline":
            String[] deadlineArgs = cmdSplit[1].split("[ \\t]+/by[ \\t]+", 2);
            if (deadlineArgs.length < 2) {
                throw new DukeException(UNKNOWN_COMMAND_MSG);
            } else {
                return new String[]{cmdSplitFront, deadlineArgs[0], deadlineArgs[1]};
            }
        case "event":
            String[] eventArgs = cmdSplit[1].split("[ \\t]+/at[ \\t]+", 2);
            if (eventArgs.length < 2) {
                throw new DukeException(UNKNOWN_COMMAND_MSG);
            } else {
                return new String[]{cmdSplitFront, eventArgs[0], eventArgs[1]};
            }
        default:
            return new String[]{};
        }
    }
}
