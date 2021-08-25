package bot;

import java.util.HashMap;
import java.util.Map;

import commands.*;

public enum CommandType {

    BYE("bye", new ByeCommand()), LIST("list", new ListCommand()),
    DONE("done", new DoneCommand()), DEADLINE("deadline", new DeadlineCommand()),
    TODO("todo", new TodoCommand()), EVENT("event", new EventCommand()),
    DELETE("delete", new DeleteCommand()), FIND("find", new FindCommand()),
    ;

    private final Command command;
    private final String name;

    // Map to retrieve enum value from name string
    private static final Map<String, CommandType> hash = new HashMap<>();

    static {
        for (CommandType c : CommandType.values()) {
            hash.put(c.getName(), c);
        }
    }

    CommandType(String name, Command cmd) {
        this.name = name;
        this.command = cmd;
    }

    /**
     * Get enum value name
     *
     * @return enum value name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Get enum value command
     *
     * @return enum value command
     */
    public Command getCommand() {
        return this.command;
    }

    /**
     * Get a command with the given name string
     *
     * @param name name string of command
     * @return command associated with name
     */
    public static Command getCommandFromName(String name) {
        CommandType cmdType = hash.get(name.toLowerCase());
        return cmdType == null ? new EmptyCommand() : cmdType.getCommand();
    }

}
