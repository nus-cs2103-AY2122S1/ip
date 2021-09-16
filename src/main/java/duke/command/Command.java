package duke.command;

import duke.exception.DukeException;
import duke.utils.Storage;
import duke.utils.TaskList;

/** Abstract class which all the commands of duke inherit from */
public abstract class Command {

    protected final String userCommand;
    protected final String userArgument;

    public final static String LIST_COMMAND = "list";
    public final static String BYE_COMMAND = "bye";
    public final static String DONE_COMMAND = "done";
    public final static String TODO_COMMAND = "todo";
    public final static String DEADLINE_COMMAND = "deadline";
    public final static String EVENT_COMMAND = "event";
    public final static String DELETE_COMMAND = "delete";
    public final static String FIND_COMMAND = "find";

    /**
     * Constructor for a command
     * @param userCommand The command the user gives
     * @param userArgument The argument (rest of the String after the command)
     */
    public Command(String userCommand, String userArgument) {
        this.userArgument = userArgument;
        this.userCommand = userCommand;
    }

    /** The operations to do on the TaskList and Storage */
    public abstract String execute(TaskList tasks, Storage storage) throws DukeException;

}
