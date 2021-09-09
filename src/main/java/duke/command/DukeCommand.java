package duke.command;

import java.util.Arrays;
import java.util.Optional;


/**
 * The DukeCommand enum encapsulates the logic for commands.
 */
public enum DukeCommand {

    BYE("bye",new ByeCommand()),
    LIST("list", new ListCommand()),
    DONE("done", new DoneCommand()),
    EVENT("event", new EventCommand()),
    DELETE("delete", new DeleteCommand()),
    DEADLINE("deadline", new DeadlineCommand()),
    TODO("todo", new TodoCommand()),
    FIND("find", new FindCommand()),
    INVALID("invalid", new InvalidCommand()),
    HELP("help", new HelpCommand());


    final String command;
    final public DukeActions action;

    /**
     * Simple constructor to initialise a duke.command.DukeCommand enum
     *
     * @param command The command that this instance serves.
     * @param action  The action that the command has.
     */
    DukeCommand(String command, DukeActions action) {
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
    public static Optional<DukeCommand> getCommand(String command) {
        return Arrays.stream(DukeCommand.values())
                .filter(x -> command.toLowerCase()
                        .startsWith(x.command))
                .findFirst();
    }

}
