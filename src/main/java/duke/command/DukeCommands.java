package duke.command;

import java.util.Arrays;
import java.util.Optional;

public enum DukeCommands {

    BYE("bye",new ByeCommand()),
    LIST("list", new ListCommand()),
    DONE("done", new DoneCommand()),
    EVENT("event", new EventCommand()),
    DELETE("delete", new DeleteCommand()),
    DEADLINE("deadline", new DeadlineCommand()),
    TODO("todo", new TodoCommand()),
    INVALID("invalid", new InvalidCommand());


    final String command;
    final public DukeActions action;

    /**
     * Simple constructor to initialise a duke.command.DukeCommands enum
     *
     * @param command The command that this instance serves.
     * @param action  The action that the command has.
     */
    DukeCommands(String command, DukeActions action) {
        this.command = command;
        this.action = action;
    }

    /**
     * Takes in a raw string command and matches the first word of the
     * command to the available enums.
     *
     * @param command The raw user input.
     * @return An optional of the enum. Returns an empty optional if not found.
     */
    public static Optional<DukeCommands> getCommand(String command) {
        return Arrays.stream(DukeCommands.values())
                .filter(x -> command.toLowerCase()
                        .startsWith(x.command))
                .findFirst();
    }

}
