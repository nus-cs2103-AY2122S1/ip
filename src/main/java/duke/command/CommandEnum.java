package duke.command;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    private static final String ERROR_INVALID_COMMAND_TYPE = "OOPS!!! This is an invalid command type.";
    private final String commandType;

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

    public static String[] getListOfCommands() {
        List<CommandEnum> listOfCommands = Arrays.asList(CommandEnum.values());
        List<String> listOfStringCommands = listOfCommands
            .stream()
            .map(CommandEnum::toString)
            .collect(Collectors.toList());
        String[] result = new String[listOfStringCommands.size()];
        Arrays.setAll(result, listOfStringCommands::get);
        return result;
    }

}
//@@author
