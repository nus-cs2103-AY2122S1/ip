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
     * Set up a command using the command, arguments and task list
     *
     * @param cmdArgsArr String array of command , optional 2nd member arguments
     * @param taskList   list of tasks stored by Duke
     */
    public Command(String[] cmdArgsArr, TaskArrayList taskList) {
        this.cmdArgsArr = cmdArgsArr;
        this.taskList = taskList;
    }


    /**
     * Runs the command.
     *
     * @return String to print on the chatbot
     * @throws DukeException
     */
    public abstract String run() throws DukeException;


}
