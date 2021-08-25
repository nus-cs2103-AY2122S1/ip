
package duke;

import duke.Tasks.BaseTask;

/**
 * This class encapsulates the command parser for Duke which process the commands
 * inputted into Duke by users.
 */
public class DukeCommandParser {

    public enum CommandType {
        ADD_TASK,
        BYE,
        LIST,
        MARK_TASK_DONE,
        DEL_TASK,
        UNKNOWN
    }



    /**
     * Starts up a new command parser for use with Duke.
     */
    public DukeCommandParser() {

    }

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
        } else if (currTaskType != BaseTask.TaskType.NONE) {
            return CommandType.ADD_TASK;

        } else {
            return CommandType.UNKNOWN;
        }
    }

}