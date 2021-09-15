package duke.command;

import duke.exception.DukeException;
import duke.Storage;
import duke.TaskList;

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

    public Command(String userCommand, String userArgument) {
        this.userArgument = userArgument;
        this.userCommand = userCommand;
    }

    public abstract String execute(TaskList tasks, Storage storage) throws DukeException;

}
