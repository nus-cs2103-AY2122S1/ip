
package duke;

/**
 * This class encapsulates the command parser for Duke which process the commands
 * inputted into Duke by users.
 */
public class DukeCommandParser {

    /**
     * Identifies the different possible command types.
     */
    public enum CommandType {
        ADD_TASK,
        BYE,
        LIST,
        MARK_TASK_DONE,
        DEL_TASK,
        UNKNOWN,
        FIND,
        HELP
    }



    /**
     * Starts up a new command parser for use with Duke.
     */
    public DukeCommandParser() {

    }

    /**
     * Parses the given command input and return the command type.
     *
     * @param cmd The command input to parse.
     * @return the type of the command.
     */
    public CommandType parse(String cmd) {

        BaseTask.TaskType currTaskType = BaseTask.checkTaskType(cmd);

        if (cmd.equals("bye")) {
            return CommandType.BYE;
        } else if (cmd.equals("list")) {
            return CommandType.LIST;
        } else if (cmd.length() >= 4 && cmd.substring(0, 4).equals("done")) {
            return CommandType.MARK_TASK_DONE;
        } else if (cmd.length() >= 6 && cmd.substring(0, 6).equals("delete")) {
            return CommandType.DEL_TASK;
        } else if (cmd.startsWith("find")) {
            return CommandType.FIND;
        } else if (cmd.equals("help") || cmd.startsWith("help ")) {
            return CommandType.HELP;
        } else if (currTaskType != BaseTask.TaskType.NONE) {
            return CommandType.ADD_TASK;
        } else {
            return CommandType.UNKNOWN;
        }
    }
}
