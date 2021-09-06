package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;

public abstract class Command {

    protected final String userCommand;
    protected final String userArgument;

    public static final String LIST_COMMAND = "list";
    public static final String BYE_COMMAND = "bye";
    public static final String DONE_COMMAND = "done";
    public static final String TODO_COMMAND = "todo";
    public static final String DEADLINE_COMMAND = "deadline";
    public static final String EVENT_COMMAND = "event";
    public static final String DELETE_COMMAND = "delete";
    public static final String FIND_COMMAND = "find";

    public Command(String userCommand, String userArgument) {
        this.userArgument = userArgument;
        this.userCommand = userCommand;
    }

    public abstract String execute(TaskList tasks, Storage storage) throws DukeException;

}
