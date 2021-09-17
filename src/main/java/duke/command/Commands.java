package duke.command;

import java.util.List;

import duke.io.AliasStorage;

/**
 * Enum of commands to cycle through and return the right command that matches user input.
 */
public enum Commands {
    EXIT (new ExitCommand()),
    LIST (new ListCommand()),
    TODO (new ToDoCommand()),
    DEADLINE (new DeadlineCommand()),
    EVENT (new EventCommand()),
    DONE (new DoneCommand()),
    DELETE (new DeleteCommand()),
    FIND (new FindCommand()),
    ALIAS (new AliasCommand());

    private final Command command;
    Commands(Command command) {
        this.command = command;
    }

    /**
     * Returns the command of this type.
     *
     * @param firstWord The first word of the user input.
     * @return The Command that uses the input as a command, or null if no Command matches it.
     */
    public static Command findCommand(String firstWord) {
        for (Commands command : Commands.values()) {
            Command commandObject = command.command;
            if (commandObject.containsCommand(firstWord)) {
                return commandObject;
            }
        }

        // if no command matches, returns null
        return null;
    }

    /**
     * Returns a string representing all aliases to save for persistence.
     *
     * @return String representation of all aliases.
     */
    public static String getAliasSaves() {
        StringBuilder sb = new StringBuilder();
        for (Commands commands : Commands.values()) {
            sb.append(commands.command.getSaveFormat());
            sb.append('\n');
        }

        // delete the last newline character
        sb.deleteCharAt(sb.length() - 1);

        return sb.toString();
    }

    /**
     * Loads the aliases of each command from the AliasStorage.
     * AliasStorage should have been loaded before calling this.
     */
    public static void loadAliases() {
        for (Commands commands : Commands.values()) {
            Command command = commands.command;
            List<String> aliases = AliasStorage.getLoadedAliases(command.getMainCommand());
            command.setAliases(aliases);
        }
    }
}
