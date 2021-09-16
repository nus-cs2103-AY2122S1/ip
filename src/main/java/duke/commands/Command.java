package duke.commands;

import duke.TaskArrayList;
import duke.exceptions.DukeException;

/**
 * Abstract base class for commands
 */
abstract class Command {
    public static String HELP_DESCRIPTION;
    public static String HELP_USAGE;

    protected String[] cmdArgsArr;
    protected TaskArrayList taskList;

    /**
     * Sets up a command using the command, arguments and task list
     *
     * @param cmdArgsArr String array of command , optional 2nd member arguments
     * @param taskList   list of tasks stored by Duke
     */
    public Command(String[] cmdArgsArr, TaskArrayList taskList) {
        this.cmdArgsArr = cmdArgsArr;
        this.taskList = taskList;
    }

    /**
     * Tests if cmdArgs has the wrong length.
     *
     * @param desiredValue length of array {cmd,args}
     * @return True if length does not match desired value
     */
    protected boolean hasWrongArgumentCount (int desiredValue) {
        return !(cmdArgsArr.length == desiredValue);
    }

    /**
     * Tests if arguments contain the desired marker.
     *
     * @param marker String that precedes an argument
     * @return True if the desired marker is missing
     */
    protected boolean isMissingArgument (String marker) {
        assert cmdArgsArr.length == 2;
        return cmdArgsArr[1].split(marker, 2).length != 2;
    }

    protected boolean isNotNumericArgument (String arg) {
        return !arg.matches("[0-9]+");
    }

    /**
     * Runs the command.
     *
     * @return String to print on the chatbot
     * @throws DukeException
     */
    public abstract String run() throws DukeException;

}
