package duke.commands;

import duke.exceptions.DukeException;

public class CommandHelp extends Command {
    public static final String HELP_COMMAND = "help";
    public static final String HELP_DESCRIPTION = "Provide help information for Duke commands";
    public static final String HELP_USAGE = "Usage: Help [command]\n"
            + "Display this help message\n"
            + "Options:\n"
            + "\tCommand (optional) - displays help information on that command";

    /**
     * Parse the help command arguments and return appropriate response
     *
     * @param cmdArgsArr 2 member String array containing [command, arguments]
     * @return relevant help message
     * @throws DukeException
     */
    public static String parse(String[] cmdArgsArr) throws DukeException {
        //if no args --> return all Helps
        if (cmdArgsArr.length == 1) {
            return CommandHelp.getAllHelp();
        }
        switch (cmdArgsArr[1]) {

        case ("bye"):
            return CommandBye.HELP_USAGE;
        case ("delete"):
            return CommandDelete.HELP_USAGE;
        case ("done"):
            return CommandDone.HELP_USAGE;
        case ("find"):
            return CommandFind.HELP_USAGE;
        case ("help"):
            return CommandHelp.HELP_USAGE;
        case ("list"):
            return CommandList.HELP_USAGE;

        case ("todo"):
            return CommandTodo.HELP_USAGE;
        case ("deadline"):
            return CommandDeadline.HELP_USAGE;
        case ("event"):
            return CommandEvent.HELP_USAGE;
        default:
            // invalid data defaults to base help
            return CommandHelp.getAllHelp();
        }
    }

    /**
     * Overview of valid commands
     *
     * @return String of valid commands and brief description
     */
    private static String getAllHelp() {
        return ""
                + CommandBye.HELP_COMMAND + "\t\t" + CommandBye.HELP_DESCRIPTION + "\n"
                + CommandDelete.HELP_COMMAND + "\t" + CommandDelete.HELP_DESCRIPTION + "\n"
                + CommandDone.HELP_COMMAND + "\t" + CommandDone.HELP_DESCRIPTION + "\n"
                + CommandFind.HELP_COMMAND + "\t\t" + CommandFind.HELP_DESCRIPTION + "\n"
                + CommandHelp.HELP_COMMAND + "\t\t" + CommandHelp.HELP_DESCRIPTION + "\n"
                + CommandList.HELP_COMMAND + "\t\t" + CommandList.HELP_DESCRIPTION + "\n"

                + CommandTodo.HELP_COMMAND + "\t\t" + CommandTodo.HELP_DESCRIPTION + "\n"
                + CommandDeadline.HELP_COMMAND + "\t" + CommandDeadline.HELP_DESCRIPTION + "\n"
                + CommandEvent.HELP_COMMAND + "\t" + CommandEvent.HELP_DESCRIPTION + "\n"
                + "";
    }

    //Each command's help string can be defined here
    private static String helpBye() {
        return CommandBye.HELP_USAGE;
    }
    private static String helpList() {
        return CommandList.HELP_USAGE;
    }
    private static String helpDone() {
        return CommandBye.HELP_USAGE;
    }
    private static String helpDelete() {
        return CommandDelete.HELP_USAGE;
    }
    private static String helpFind() {
        return CommandFind.HELP_USAGE;
    }

    // new tasks
    private static String helpTodo() {
        return CommandTodo.HELP_USAGE;
    }
    private static String helpDeadline() {
        return CommandDeadline.HELP_USAGE;
    }
    private static String helpEvent() {
        return CommandEvent.HELP_USAGE;
    }
}
