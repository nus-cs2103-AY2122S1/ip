package duke.command;

import duke.exception.DukeException;

public enum CommandEnum {

//@@author {FergusMok}--reused
//{The following code is reused from Bryan Loh's repository. It is an exemplary method of implementing enums.}
//{https://github.com/Anonymxtrix/ip/blob/master/src/main/java/duke/request/Command.java}

    BYE("bye"),
    LIST("list"),
    DONE("done"),
    DELETE("delete"),
    SAVE("save"),
    FIND("find"),
    HELP("help"),
    RESET("reset"),
    EVENT("event"),
    DEADLINE("deadline"),
    TODO("todo");

    private final String commandType;
    private static final String ERROR_INVALID_COMMAND_TYPE = "☹ OOPS!!! This is an invalid command type.";

    CommandEnum(String commandType) {
        this.commandType = commandType;
    }

    @Override
    public String toString() {
        return this.commandType;
    }

    public static CommandEnum getCommand(String command) throws DukeException {
        for (CommandEnum task : CommandEnum.values()) {
            if (task.commandType.equals(command)) {
                return task;
            }
        }
        throw new DukeException(ERROR_INVALID_COMMAND_TYPE);
    }

}

//@@author