package agent.command;

import java.util.Arrays;

/**
 * Represents a fixed list of supported user commands.
 *
 * @author kevin9foong
 */
public enum CommandType {
    LIST, TODO, DEADLINE, EVENT, DELETE, DONE, FIND, REMINDER, BYE;

    /**
     * Returns Command classification based on the given string.
     *
     * @param commandText string which matches command name.
     * @return Command representing the given string command, or null if no command matched.
     */
    public static CommandType getCommand(String commandText) {
        return Arrays.stream(CommandType.values())
                .reduce(null, (selectedCmd, currentCmd) ->
                        selectedCmd == null && commandText.toUpperCase().equals(currentCmd.toString())
                                ? currentCmd
                                : selectedCmd);
    }


}
